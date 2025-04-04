import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Empresa } from '../models/empresa.model';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';



@Injectable({
  providedIn: 'root',
})
export class EmpresaService {
  private readonly apiUrl = 'http://localhost:8080/api/empresas';

  private http = inject(HttpClient); // Injeção moderna

  createEmpresa(empresa: Empresa): Observable<Empresa> {
    return this.http.post<Empresa>(this.apiUrl, empresa);
  }

  associarFornecedor(empresaId: number, fornecedorId: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${empresaId}/fornecedores/${fornecedorId}`, {});
  }

  searchEmpresas(nomeFantasia?: string, cnpj?: string): Observable<Empresa[]> {
    let params = new HttpParams();
    if (nomeFantasia) params = params.set('nomeFantasia', nomeFantasia);
    if (cnpj) params = params.set('cnpj', cnpj);

    return this.http.get<Empresa[]>(this.apiUrl, { params });
  }

  buscarEmpresasComFornecedores(): Observable<Empresa[]> {
    return this.http.get<Empresa[]>(`${this.apiUrl}/com-fornecedores`);
  }

  getEmpresas(): Observable<Empresa[]> {
    return this.http.get<Empresa[]>(this.apiUrl);
  }

  updateEmpresa(cnpj: string, empresa: Empresa): Observable<Empresa> {
    return this.http.put<Empresa>(`${this.apiUrl}/${cnpj}`, empresa);
  }

  deleteEmpresa(cnpj: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${cnpj}`).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Erro ao excluir empresa:', error);
        return throwError(() => new Error('Erro ao excluir a empresa. Verifique se ela existe.'));
      })
    );
  }
  
  
}
