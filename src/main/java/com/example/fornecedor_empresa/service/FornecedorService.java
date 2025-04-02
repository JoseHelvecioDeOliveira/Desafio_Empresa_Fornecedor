package com.example.fornecedor_empresa.service;

import com.example.fornecedor_empresa.model.Empresa;
import com.example.fornecedor_empresa.model.EmpresaFornecedor;
import com.example.fornecedor_empresa.model.Fornecedor;
import com.example.fornecedor_empresa.repository.EmpresaFornecedorRepository;
import com.example.fornecedor_empresa.repository.EmpresaRepository;
import com.example.fornecedor_empresa.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaFornecedorRepository empresaFornecedorRepository;

    private boolean validarCPF(String cpf) {
        // Remove qualquer formatação
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 caracteres e não tem todos os números iguais
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Validação do primeiro dígito verificador
        int soma = 0, resto;
        for (int i = 0; i < 9; i++) {
            soma += (10 - i) * (cpf.charAt(i) - '0');
        }
        resto = soma % 11;
        if (resto == 1 || resto == 0) {
            if (cpf.charAt(9) != '0') return false;
        } else {
            if (cpf.charAt(9) != (11 - resto) + '0') return false;
        }

        // Validação do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (11 - i) * (cpf.charAt(i) - '0');
        }
        resto = soma % 11;
        return resto < 2 ? cpf.charAt(10) == '0' : cpf.charAt(10) == (11 - resto) + '0';
    }


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


    public Fornecedor createFornecedor(Fornecedor fornecedor) throws Exception {
        if (fornecedorRepository.findByCnpjCpf(fornecedor.getCnpjCpf()).isPresent()) {
            throw new Exception("Fornecedor já cadastrado com esse CNPJ/CPF.");
        }

        // Validação de CPF/CNPJ
        if (!CnpjCpfValidator.isValid(fornecedor.getCnpjCpf())) {
            throw new Exception("CNPJ/CPF inválido: " + fornecedor.getCnpjCpf());
        }


        // Validação: Verifica se o CEP é válido
        if (!cepService.validarCep(fornecedor.getCep())) {
            throw new Exception("CEP inválido.");
        }

        // Validação: Verifica se o fornecedor é pessoa física e se possui RG e Data de Nascimento
        if (fornecedor.getCnpjCpf().length() == 11 &&
                (fornecedor.getRg() == null || fornecedor.getDataNascimento() == null)) {
            throw new Exception("Fornecedor pessoa física precisa de RG e Data de Nascimento.");
        }

        // Validação: Verifica se o fornecedor está no Paraná (CEP começando com "80")
        if (fornecedor.getCep().startsWith("80")) {
            // É necessário validar a data de nascimento
            if (fornecedor.getDataNascimento() == null) {
                throw new Exception("Data de nascimento é obrigatória para fornecedores no Paraná.");
            }

            if (LocalDate.now().minusYears(18).isBefore(fornecedor.getDataNascimento())) {
                throw new Exception("Fornecedor menor de idade não pode ser cadastrado no Paraná.");
            }
        }

            // Se todas as validações passarem, salva o fornecedor no banco

        return fornecedorRepository.save(fornecedor);
    }

    public EmpresaFornecedor associarEmpresa(Long fornecedorId, Long empresaId) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        EmpresaFornecedor empresaFornecedor = new EmpresaFornecedor();
        empresaFornecedor.setEmpresa(empresa);
        empresaFornecedor.setFornecedor(fornecedor);

        return empresaFornecedorRepository.save(empresaFornecedor);
    }

    public Optional<Fornecedor> buscarFornecedorPorCnpjCpf(String cnpjCpf) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findByCnpjCpf(cnpjCpf);
        System.out.println("Buscando fornecedor: " + cnpjCpf + " - Encontrado: " + fornecedor.isPresent());
        return fornecedor;
    }

    public List<Fornecedor> searchFornecedores(String nome, String cnpjCpf) {
        if ((nome == null || nome.isEmpty()) && (cnpjCpf == null || cnpjCpf.isEmpty())) {
            return fornecedorRepository.findAll(); // Retorna todos se nenhum critério for informado
        } else if (nome != null && !nome.isEmpty() && cnpjCpf != null && !cnpjCpf.isEmpty()) {
            return fornecedorRepository.findByNomeContainingAndCnpjCpfContaining(nome, cnpjCpf);
        } else if (nome != null && !nome.isEmpty()) {
            return fornecedorRepository.findByNomeContaining(nome);
        } else {
            return fornecedorRepository.findByCnpjCpfContaining(cnpjCpf);
        }
    }   

    public void deleteFornecedor(String cnpjCpf) throws FornecedorNotFoundException {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findByCnpjCpf(cnpjCpf);
        if (fornecedor.isPresent()) {
            fornecedorRepository.delete(fornecedor.get());
        } else {
            throw new FornecedorNotFoundException("Fornecedor não encontrado.");
        }
    }

    public Fornecedor updateFornecedor(String cnpjCpf, Fornecedor fornecedorAtualizado) throws FornecedorNotFoundException {
        // Verifica se o fornecedor existe
        Optional<Fornecedor> fornecedorExistente = fornecedorRepository.findByCnpjCpf(cnpjCpf);

        if (fornecedorExistente.isPresent()) {
            // Atualiza os dados do fornecedor
            Fornecedor fornecedor = fornecedorExistente.get();
            fornecedor.setNome(fornecedorAtualizado.getNome());
            fornecedor.setEmail(fornecedorAtualizado.getEmail());
            fornecedor.setCnpjCpf(fornecedorAtualizado.getCnpjCpf()); // Se necessário, você pode validar o CNPJ/CPF
            fornecedor.setCep(fornecedorAtualizado.getCep());

            // Salva as alterações
            return fornecedorRepository.save(fornecedor);
        } else {
            throw new FornecedorNotFoundException("Fornecedor não encontrado.");
        }
    }
}
