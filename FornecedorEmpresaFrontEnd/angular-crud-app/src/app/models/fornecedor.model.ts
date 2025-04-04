import { Empresa } from './empresa.model';

export interface Fornecedor {
  id?: number; 
  cnpjCpf: string;
  nome: string;
  email: string;
  telefone: string;
  cep: string;
  rg?: string;
  dataNascimento?: string; // Opcional para casos em que não seja necessário
  empresas?: Empresa[]; // Relacionamento com empresas
}
