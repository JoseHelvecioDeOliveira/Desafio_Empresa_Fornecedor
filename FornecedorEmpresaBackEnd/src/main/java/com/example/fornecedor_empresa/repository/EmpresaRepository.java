package com.example.fornecedor_empresa.repository;

import com.example.fornecedor_empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCnpj(String cnpj);  // Buscar por CNPJ
    List<Empresa> findByNomeFantasiaContaining(String nomeFantasia);  // Buscar por nome fantasia
    List<Empresa> findByCnpjContaining(String cnpj);  // Buscar por CNPJ com filtro parcial
    List<Empresa> findByNomeFantasiaContainingAndCnpjContaining(String nomeFantasia, String cnpj);

    @Query("SELECT e FROM Empresa e LEFT JOIN FETCH e.fornecedores")
    List<Empresa> findAllWithFornecedores();// Buscar por nome fantasia e CNPJ
}
