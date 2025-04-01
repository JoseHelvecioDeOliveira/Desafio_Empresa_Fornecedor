package com.example.fornecedor_empresa.repository;

import com.example.fornecedor_empresa.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByCnpjCpf(String cnpjCpf);
    List<Fornecedor> findByNomeContaining(String nome);
    List<Fornecedor> findByCnpjCpfContaining(String cnpjCpf);
}

