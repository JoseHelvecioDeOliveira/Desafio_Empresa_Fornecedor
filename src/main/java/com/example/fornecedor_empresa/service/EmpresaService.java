package com.example.fornecedor_empresa.service;

import com.example.fornecedor_empresa.model.Empresa;
import com.example.fornecedor_empresa.model.EmpresaFornecedor;
import com.example.fornecedor_empresa.model.Fornecedor;
import com.example.fornecedor_empresa.repository.EmpresaFornecedorRepository;
import com.example.fornecedor_empresa.repository.EmpresaRepository;
import com.example.fornecedor_empresa.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private EmpresaFornecedorRepository empresaFornecedorRepository;

    // metodo para criação da empresa
    public Empresa createEmpresa(Empresa empresa) throws Exception {
        if (empresaRepository.findByCnpj(empresa.getCnpj()).isPresent()) {
            throw new Exception("Empresa já cadastrada com esse CNPJ.");
        }
        return empresaRepository.save(empresa);  // Salva a empresa no banco de dados
    }

    public EmpresaFornecedor associarFornecedor(Long empresaId, Long fornecedorId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        EmpresaFornecedor empresaFornecedor = new EmpresaFornecedor();
        empresaFornecedor.setEmpresa(empresa);
        empresaFornecedor.setFornecedor(fornecedor);

        return empresaFornecedorRepository.save(empresaFornecedor);
    }


    // metodo para buscar empresas com filtros
    public List<Empresa> searchEmpresas(String nomeFantasia, String cnpj) {
        if (nomeFantasia != null && !nomeFantasia.isEmpty() && cnpj != null && !cnpj.isEmpty()) {
            return empresaRepository.findByNomeFantasiaContainingAndCnpjContaining(nomeFantasia, cnpj);
        } else if (nomeFantasia != null && !nomeFantasia.isEmpty()) {
            return empresaRepository.findByNomeFantasiaContaining(nomeFantasia);
        } else if (cnpj != null && !cnpj.isEmpty()) {
            return empresaRepository.findByCnpjContaining(cnpj);
        } else {
            return empresaRepository.findAll(); // Retorna todas as empresas se nenhum filtro for passado
        }
    }


    // metodo para buscar empresa por CNPJ
    public Empresa getEmpresa(String cnpj) throws Exception {
        return empresaRepository.findByCnpj(cnpj).orElseThrow(() -> new Exception("Empresa não encontrada."));
    }

    // metodo para atualizar empresa
    public Empresa updateEmpresa(String cnpj, Empresa empresaAtualizada) throws Exception {
        Empresa empresa = empresaRepository.findByCnpj(cnpj).orElseThrow(() -> new Exception("Empresa não encontrada."));
        empresa.setNomeFantasia(empresaAtualizada.getNomeFantasia());
        empresa.setCep(empresaAtualizada.getCep());
        return empresaRepository.save(empresa);
    }

    // metodo para deletar empresa
    public void deleteEmpresa(String cnpj) throws Exception {
        Empresa empresa = empresaRepository.findByCnpj(cnpj).orElseThrow(() -> new Exception("Empresa não encontrada."));
        empresaRepository.delete(empresa);
    }
}
