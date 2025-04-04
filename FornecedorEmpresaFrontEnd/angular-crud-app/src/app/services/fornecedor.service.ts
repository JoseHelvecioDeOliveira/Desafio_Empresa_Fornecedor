import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Fornecedor } from '../models/fornecedor.model';
import { format } from 'date-fns';
import { parse } from 'date-fns';

@Injectable({
  providedIn: 'root',
})
export class FornecedorService {
  private readonly apiUrl = 'http://localhost:8080/api/fornecedores';

  private http = inject(HttpClient); // Injeção moderna

  createFornecedor(fornecedor: Fornecedor): Observable<Fornecedor> {
    console.log(fornecedor.dataNascimento)
    const fornecedorFormatado = {
      ...fornecedor,
      dataNascimento: fornecedor.dataNascimento // Converte para formato aceito pelo backend
    };

    return this.http.post<Fornecedor>(this.apiUrl, fornecedorFormatado);
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
    const fornecedorFormatado = {
      ...fornecedor,
      dataNascimento: fornecedor.dataNascimento
    };

    return this.http.put<Fornecedor>(`${this.apiUrl}/${cnpjCpf}`, fornecedorFormatado);
  }

  deleteFornecedor(cnpjCpf: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${cnpjCpf}`);
  }
}
