import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CnpjCpfValidatorService {
  private CPF_PATTERN = /^\d{11}$/;
  private CNPJ_PATTERN = /^\d{14}$/;

  isValid(documento: string): boolean {
    if (!documento) return false;

    documento = documento.replace(/\D/g, ''); // Remove caracteres não numéricos

    return this.CPF_PATTERN.test(documento) ? this.validarCPF(documento) : 
           this.CNPJ_PATTERN.test(documento) ? this.validarCNPJ(documento) : false;
  }

  private validarCPF(cpf: string): boolean {
    if (/^(\d)\1{10}$/.test(cpf)) return false;

    let soma = 0, resto;
    for (let i = 0; i < 9; i++) {
      soma += (10 - i) * +cpf[i];
    }
    resto = soma % 11;
    const digito1 = resto < 2 ? 0 : 11 - resto;
    if (digito1 !== +cpf[9]) return false;

    soma = 0;
    for (let i = 0; i < 10; i++) {
      soma += (11 - i) * +cpf[i];
    }
    resto = soma % 11;
    const digito2 = resto < 2 ? 0 : 11 - resto;

    return digito2 === +cpf[10];
  }

  private validarCNPJ(cnpj: string): boolean {
    if (/^(\d)\1{13}$/.test(cnpj)) return false;

    const multiplicadores1 = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2];
    const multiplicadores2 = [6, ...multiplicadores1];

    let soma = 0;
    for (let i = 0; i < 12; i++) {
      soma += +cnpj[i] * multiplicadores1[i];
    }
    let resto = soma % 11;
    const digito1 = resto < 2 ? 0 : 11 - resto;
    if (digito1 !== +cnpj[12]) return false;

    soma = 0;
    for (let i = 0; i < 13; i++) {
      soma += +cnpj[i] * multiplicadores2[i];
    }
    resto = soma % 11;
    const digito2 = resto < 2 ? 0 : 11 - resto;

    return digito2 === +cnpj[13];
  }
}
