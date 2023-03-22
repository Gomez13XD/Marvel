package com.Marvel.api;

import javax.servlet.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MarvelApi implements Servlet {
    private static final String Base_URL = "https://gateway.marvel.com/v1/public/characters/";
    private static final String Api_KEY = "df45939d3bb1217bb68b93bfa062f71d";
    //String PersonajeId = "1009610";

    public static String getCharById(int id) throws URISyntaxException,IOException, InterruptedException {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest getChar = HttpRequest.newBuilder()
                    .uri(URI.create(Base_URL + id + "?ts=1000&apikey=" + Api_KEY + "&hash=53a263eac9c88c6922ad7a61d9285e85"))
                    .header("Accept","application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(getChar, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
                return response.body();
            } else if (response.statusCode() == 404) {
            return "El personaje no fue encontrado";
        } else {
                throw new RuntimeException("No se pudo obtener el personaje");
            }

    }

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
