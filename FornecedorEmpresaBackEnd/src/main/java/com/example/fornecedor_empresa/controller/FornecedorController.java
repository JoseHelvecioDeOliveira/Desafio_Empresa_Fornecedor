package com.example.fornecedor_empresa.controller;

import com.example.fornecedor_empresa.model.EmpresaFornecedor;
import com.example.fornecedor_empresa.model.Fornecedor;
import com.example.fornecedor_empresa.service.EmpresaService;
import com.example.fornecedor_empresa.service.FornecedorNotFoundException;
import com.example.fornecedor_empresa.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedores")
@CrossOrigin(origins = "http://localhost:4200")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Fornecedor> createFornecedor(@RequestBody Fornecedor fornecedor) throws Exception {
        try {
            Fornecedor novoFornecedor = fornecedorService.createFornecedor(fornecedor);
            return ResponseEntity.ok(novoFornecedor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Retorna erro 400 se houver uma exceção
        }
    }

    @PostMapping("/{fornecedorId}/empresa/{empresaId}")
    public ResponseEntity<EmpresaFornecedor> associarEmpresa(
            @PathVariable Long fornecedorId,
            @PathVariable Long empresaId) {

        EmpresaFornecedor empresaFornecedor = empresaService.associarFornecedor(empresaId, fornecedorId);
        return ResponseEntity.ok(empresaFornecedor);
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getFornecedores(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cnpjCpf) {
        return ResponseEntity.ok(fornecedorService.searchFornecedores(nome, cnpjCpf));
    }
    @DeleteMapping("/{cnpjCpf}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable String cnpjCpf) {
        try {
            fornecedorService.deleteFornecedor(cnpjCpf);
            return ResponseEntity.noContent().build();
        } catch (FornecedorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Fornecedor>> buscarFornecedores(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cnpjCpf) {
        List<Fornecedor> fornecedores = fornecedorService.searchFornecedores(nome, cnpjCpf);
        return fornecedores.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(fornecedores);
    }


    @PutMapping("/{cnpjCpf}")
    public ResponseEntity<Fornecedor> updateFornecedor(
            @PathVariable String cnpjCpf,
            @RequestBody Fornecedor fornecedorAtualizado) {
        try {
            Fornecedor fornecedor = fornecedorService.updateFornecedor(cnpjCpf, fornecedorAtualizado);
            return ResponseEntity.ok(fornecedor);  // Retorna o fornecedor atualizado com status 200 OK
        } catch (FornecedorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se o fornecedor não for encontrado
        }
    }

}
