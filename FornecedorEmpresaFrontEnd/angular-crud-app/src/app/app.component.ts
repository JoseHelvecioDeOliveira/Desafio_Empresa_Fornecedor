import { Component, OnInit } from '@angular/core';
import { Fornecedor } from './models/fornecedor.model';
import { Empresa } from './models/empresa.model';
import { EmpresaService } from './services/empresa.service';
import { FornecedorService } from './services/fornecedor.service';
import { FormsModule } from '@angular/forms'; // Importação do FormsModule
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  empresas: any[] = [];
  fornecedores: any[] = [];

  novaEmpresa = { 
    nomeFantasia: '',
     cnpj: '',
      cep: '' 
    }

    empresaSelecionada: Empresa | null = null;
    fornecedorSelecionado: Fornecedor | null = null;

  novoFornecedor = { 
    nome: '', 
    cnpjCpf: '', 
    cep: '', 
    email: '', 
    telefone: '', 
    rg: '', 
    dataNascimento: '' 
  };

  constructor(
    private empresaService: EmpresaService,
    private fornecedorService: FornecedorService
  ) {}

  ngOnInit() {
    this.getEmpresas();
    this.getFornecedores();
  }

  // Buscar empresas
  getEmpresas() {
    this.empresaService.getEmpresas().subscribe((data) => {
      this.empresas = data;
    });
  }  

  // Adicionar nova empresa
  addEmpresa() {
    this.empresaService.createEmpresa(this.novaEmpresa).subscribe(() => {
      this.getEmpresas();
      this.novaEmpresa = { nomeFantasia: '', cnpj: '', cep: '' }; // Resetar formulário
    });
  }

  // Abrir modal para editar empresa
  editEmpresa(empresa: Empresa) {
    this.empresaSelecionada = { ...empresa }; // Clonar para evitar edição direta
  }

  // Salvar edição da empresa
  salvarEdicaoEmpresa() {
    if (this.empresaSelecionada) {
      this.empresaService.updateEmpresa(this.empresaSelecionada.cnpj, this.empresaSelecionada)
        .subscribe(() => {
          this.getEmpresas();
          this.empresaSelecionada = null;
        });
    }
  }

  fecharModalEmpresa() {
    this.empresaSelecionada = null;
  }

 
  // Excluir empresa
  deleteEmpresa(cnpj: string) { 
    this.empresaService.deleteEmpresa(cnpj).subscribe(() => {
        this.getEmpresas();
    });
}

  // Buscar fornecedores
  getFornecedores() {
    this.fornecedorService.getFornecedores().subscribe((data) => {
      this.fornecedores = data;
    });
  }

  // Adicionar novo fornecedor**
  addFornecedor() {
    this.fornecedorService.createFornecedor(this.novoFornecedor).subscribe(() => {
      this.getFornecedores();
      this.novoFornecedor = { 
        nome: '', 
        cnpjCpf: '', 
        email: '', 
        telefone: '', 
        rg: '', 
        cep: '', 
        dataNascimento: '' 
      }; // Resetando os campos corretamente
    });
  }

  // Abrir modal para editar fornecedor
  editFornecedor(fornecedor: Fornecedor) {
    this.fornecedorSelecionado = { ...fornecedor };
  }

  // Salvar edição do fornecedor
  salvarEdicaoFornecedor() {
    if (this.fornecedorSelecionado) {
      this.fornecedorService.updateFornecedor(this.fornecedorSelecionado.cnpjCpf, this.fornecedorSelecionado)
        .subscribe(() => {
          this.getFornecedores();
          this.fornecedorSelecionado = null;
        });
    }
  }

  fecharModalFornecedor() {
    this.fornecedorSelecionado = null;
  }

  // Excluir fornecedor
  deleteFornecedor(cnpjCpf: string) {
    this.fornecedorService.deleteFornecedor(cnpjCpf).subscribe(() => {
      this.getFornecedores();
    });
  }
}
