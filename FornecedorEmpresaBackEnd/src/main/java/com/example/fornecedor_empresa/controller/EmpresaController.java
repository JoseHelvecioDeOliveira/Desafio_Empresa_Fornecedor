package com.example.fornecedor_empresa.controller;

import com.example.fornecedor_empresa.model.Empresa;
import com.example.fornecedor_empresa.model.EmpresaFornecedor;
import com.example.fornecedor_empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;


    // Criação de empresa
    @PostMapping
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        try {
            Empresa novaEmpresa = empresaService.createEmpresa(empresa);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaEmpresa); // Retorna 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Retorna erro 400 em caso de exceção
        }
    }

    @PostMapping("/{empresaId}/fornecedores/{fornecedorId}")
    public ResponseEntity<EmpresaFornecedor> associarFornecedor(
            @PathVariable Long empresaId,
            @PathVariable Long fornecedorId) {

        EmpresaFornecedor empresaFornecedor = empresaService.associarFornecedor(empresaId, fornecedorId);
        return ResponseEntity.ok(empresaFornecedor);
    }

    // Buscar todas as empresas com filtros opcionais
    @GetMapping
    public ResponseEntity<List<Empresa>> getEmpresas(
            @RequestParam(required = false) String nomeFantasia,
            @RequestParam(required = false) String cnpj) {
        List<Empresa> empresas = empresaService.searchEmpresas(nomeFantasia, cnpj);
        return ResponseEntity.ok(empresas); // Retorna 200 OK com a lista de empresas
    }

    // Buscar empresa por CNPJ
    @GetMapping("/{cnpj}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable String cnpj) {
        try {
            Empresa empresa = empresaService.getEmpresa(cnpj);
            return ResponseEntity.ok(empresa); // Retorna a empresa com 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se empresa não for encontrada
        }
    }

    @GetMapping("/com-fornecedores")
    public ResponseEntity<List<Empresa>> getEmpresasComFornecedores() {
        List<Empresa> empresas = empresaService.buscarEmpresasComFornecedores();
        return ResponseEntity.ok(empresas);
    }



    // Deletar empresa por CNPJ
    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable String cnpj) {
        try {
            empresaService.deleteEmpresa(cnpj);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se empresa não for encontrada
        }
    }


    // Atualizar empresa
    @PutMapping("/{cnpj}")
    public ResponseEntity<Empresa> updateEmpresa(
            @PathVariable String cnpj,
            @RequestBody Empresa empresaAtualizada) {
        try {
            Empresa empresa = empresaService.updateEmpresa(cnpj, empresaAtualizada);
            return ResponseEntity.ok(empresa); // Retorna a empresa atualizada com 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se a empresa não for encontrada
        }
    }
}
