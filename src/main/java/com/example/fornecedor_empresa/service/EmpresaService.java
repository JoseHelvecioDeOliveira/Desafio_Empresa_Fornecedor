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

    public static boolean validarCNPJ(String cnpj) {
        if (cnpj == null) return false;

        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se o CNPJ tem 14 dígitos e não é uma sequência repetida
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        int soma = 0, resto;
        int[] multiplicadores1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadores2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Cálculo do primeiro dígito verificador
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * multiplicadores1[i];
        }
        resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;
        if (digito1 != Character.getNumericValue(cnpj.charAt(12))) return false;

        // Cálculo do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * multiplicadores2[i];
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return digito2 == Character.getNumericValue(cnpj.charAt(13));
    }


    // metodo para criação da empresa
    public Empresa createEmpresa(Empresa empresa) throws Exception {
        if (empresaRepository.findByCnpj(empresa.getCnpj()).isPresent()) {
            throw new Exception("Empresa já cadastrada com esse CNPJ.");
        }

        // Validação de CPF/CNPJ
        if (!CnpjCpfValidator.isValid(empresa.getCnpj())) {
            throw new Exception("CNPJ/CPF inválido: " + empresa.getCnpj());
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

    public List<Empresa> buscarEmpresasComFornecedores() {
        return empresaRepository.findAllWithFornecedores();
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
