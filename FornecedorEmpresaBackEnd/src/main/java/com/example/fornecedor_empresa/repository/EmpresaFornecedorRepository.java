package com.example.fornecedor_empresa.repository;


import com.example.fornecedor_empresa.model.EmpresaFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaFornecedorRepository extends JpaRepository<EmpresaFornecedor, Long> {
}