package com.sicredi.votacao_assembleia.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ValidadorCpfService {

    public boolean isCpfvalido(String cpf) throws IOException, InterruptedException {
        String token = "6024%7Cpj9iKUVaKS2qDzdFxQLe66Ibc8H8N1V9";

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(
                "https://api.invertexto.com/v1/validator?token=" + token + "&value="
                        + cpf + "&type=cpf"))
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.body().contains("true"))
            return true;

        return false;
    }
}
