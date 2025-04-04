import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; // Importação do FormsModule
import { CommonModule } from '@angular/common';

// Serviços
import { CepService } from './services/cep.service';
import { CnpjCpfValidatorService } from './services/cnpj-cpf-validator.service';
import { EmpresaService } from './services/empresa.service';
import { FornecedorService } from './services/fornecedor.service';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
  ],
  imports: [
    BrowserModule,
    FormsModule, 
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [ 
    CepService,
    CnpjCpfValidatorService,
    EmpresaService,
    FornecedorService
  ],  
})
export class AppModule {}
