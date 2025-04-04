import { Fornecedor } from './fornecedor.model';

export interface Empresa {
  id?: number;
  cnpj: string;
  nomeFantasia: string;
  cep: string;
  fornecedores?: Fornecedor[]; // Relacionamento com fornecedores
}
