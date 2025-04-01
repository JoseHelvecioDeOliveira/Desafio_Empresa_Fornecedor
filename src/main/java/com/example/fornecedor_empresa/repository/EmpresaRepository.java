package com.example.fornecedor_empresa.repository;

import com.example.fornecedor_empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCnpj(String cnpj);  // Buscar por CNPJ
    List<Empresa> findByNomeFantasiaContaining(String nomeFantasia);  // Buscar por nome fantasia
    List<Empresa> findByCnpjContaining(String cnpj);  // Buscar por CNPJ com filtro parcial
    List<Empresa> findByNomeFantasiaContainingAndCnpjContaining(String nomeFantasia, String cnpj);  // Buscar por nome fantasia e CNPJ
}
