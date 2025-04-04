package com.example.fornecedor_empresa.service;

import java.util.regex.Pattern;

public class CnpjCpfValidator {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("\\d{14}");

    public static boolean isValid(String documento) {
        if (documento == null) return false;

        // Remove caracteres não numéricos
        documento = documento.replaceAll("\\D", "");

        // Valida CPF
        if (CPF_PATTERN.matcher(documento).matches()) {
            return validarCPF(documento);
        }

        // Valida CNPJ
        if (CNPJ_PATTERN.matcher(documento).matches()) {
            return validarCNPJ(documento);
        }

        return false;
    }

    private static boolean validarCPF(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) return false;

        int soma = 0, resto;
        for (int i = 0; i < 9; i++) {
            soma += (10 - i) * (cpf.charAt(i) - '0');
        }
        resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;
        if (digito1 != Character.getNumericValue(cpf.charAt(9))) return false;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (11 - i) * (cpf.charAt(i) - '0');
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return digito2 == Character.getNumericValue(cpf.charAt(10));
    }

    private static boolean validarCNPJ(String cnpj) {
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        int soma = 0, resto;
        int[] multiplicadores1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadores2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * multiplicadores1[i];
        }
        resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;
        if (digito1 != Character.getNumericValue(cnpj.charAt(12))) return false;

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * multiplicadores2[i];
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return digito2 == Character.getNumericValue(cnpj.charAt(13));
    }
}
