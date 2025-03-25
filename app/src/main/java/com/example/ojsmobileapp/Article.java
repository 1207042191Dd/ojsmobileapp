package com.example.ojsmobileapp;

import java.util.List;

public class Article {
    int article_id;
    String section;
    String title;
    String doi;
    String abstractText; // Usar abstractText en lugar de "abstract" porque es palabra reservada en Java
    String date_published;
    List<Author> authors; // Lista de autores
    List<Galley> galeys; // Lista de formatos de visualización (PDF/HTML)

    // Método para obtener la URL del PDF
    public String getPdfUrl() {
        for (Galley galley : galeys) {
            if ("PDF".equalsIgnoreCase(galley.label)) {
                return galley.UrlViewGalley;
            }
        }
        return null;
    }

    // Método para obtener la URL del HTML
    public String getHtmlUrl() {
        for (Galley galley : galeys) {
            if ("HTML".equalsIgnoreCase(galley.label)) {
                return galley.UrlViewGalley;
            }
        }
        return null;
    }

    // Método para obtener los nombres de los autores en un solo String
    public String getAuthorsString() {
        if (authors == null || authors.isEmpty()) {
            return "Autor desconocido";
        }
        StringBuilder authorsString = new StringBuilder();
        for (Author author : authors) {
            authorsString.append(author.name).append(", ");
        }
        return authorsString.substring(0, authorsString.length() - 2);
    }
}
