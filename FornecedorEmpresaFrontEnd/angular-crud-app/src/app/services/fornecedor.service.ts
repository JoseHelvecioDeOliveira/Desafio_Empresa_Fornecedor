import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fornecedor } from '../models/fornecedor.model';
import { format } from 'date-fns';
import { parse } from 'date-fns';
import { catchError, switchMap } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FornecedorService {
  private readonly apiUrl = 'http://localhost:8080/api/fornecedores';
  private readonly viaCepUrl = 'https://viacep.com.br/ws';

  private http = inject(HttpClient); // Injeção moderna

  createFornecedor(fornecedor: Fornecedor): Observable<Fornecedor> {
    console.log(fornecedor.dataNascimento);

    return this.http.get<any>(`${this.viaCepUrl}/${fornecedor.cep}/json/`).pipe(
      switchMap((response) => {
        if (response.uf === 'PR') {
          const idade = this.calcularIdade(fornecedor.dataNascimento || '');
          if (idade < 18) {
            return throwError(() => new Error('Fornecedor do PR deve ser maior de idade.'));
          }
        }
        const fornecedorFormatado = {
          ...fornecedor,
          dataNascimento: fornecedor.dataNascimento,
        };

        return this.http.post<Fornecedor>(this.apiUrl, fornecedorFormatado);
      }),
      catchError((error) => {
        console.error('Erro ao criar fornecedor:', error);
        return throwError(() => error);
      })
    );
  }

  private calcularIdade(dataNascimento: string): number {
    const nascimento = new Date(dataNascimento);
    const hoje = new Date();
    let idade = hoje.getFullYear() - nascimento.getFullYear();
    const mesDiff = hoje.getMonth() - nascimento.getMonth();
    
    if (mesDiff < 0 || (mesDiff === 0 && hoje.getDate() < nascimento.getDate())) {
      idade--;
    }
    
    return idade;
  }


  getFornecedores(): Observable<Fornecedor[]> {
    return this.http.get<Fornecedor[]>(this.apiUrl);
  }

  associarEmpresa(fornecedorId: number, empresaId: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${fornecedorId}/empresas/${empresaId}`, {});
  }

  buscarFornecedorPorCnpjCpf(cnpjCpf: string): Observable<Fornecedor> {
    return this.http.get<Fornecedor>(`${this.apiUrl}/${cnpjCpf}`);
  }

  searchFornecedores(nome?: string, cnpjCpf?: string): Observable<Fornecedor[]> {
    let params = new HttpParams();
    if (nome) params = params.set('nome', nome);
    if (cnpjCpf) params = params.set('cnpjCpf', cnpjCpf);

    return this.http.get<Fornecedor[]>(this.apiUrl, { params });
  }

  updateFornecedor(cnpjCpf: string, fornecedor: Fornecedor): Observable<Fornecedor> {
    if (!fornecedor.dataNascimento) {
      return throwError(() => new Error('Data de nascimento é obrigatória.'));
    }
  
    return this.http.get<any>(`${this.viaCepUrl}/${fornecedor.cep}/json/`).pipe(
      switchMap((response) => {
        if (response.uf === 'PR') {
          const idade = this.calcularIdade(fornecedor.dataNascimento || '');
          if (idade < 18) {
            return throwError(() => new Error('Fornecedor do PR deve ser maior de idade.'));
          }
        }
  
        const fornecedorFormatado = {
          ...fornecedor,
          dataNascimento: fornecedor.dataNascimento
        };
  
        return this.http.put<Fornecedor>(`${this.apiUrl}/${cnpjCpf}`, fornecedorFormatado);
      }),
      catchError((error) => {
        console.error('Erro ao atualizar fornecedor:', error);
        return throwError(() => error);
      })
    );
  }
  

  deleteFornecedor(cnpjCpf: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${cnpjCpf}`);
  }
}
