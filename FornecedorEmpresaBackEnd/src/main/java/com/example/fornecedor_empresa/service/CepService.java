package com.example.fornecedor_empresa.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    public boolean validarCep(String cep) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            String response = restTemplate.getForObject(url, String.class);
            return response != null && !response.contains("erro");
        } catch (Exception e) {
            return false;
        }
    }
}

