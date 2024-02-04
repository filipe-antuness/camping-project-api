package com.api.campingproject.core.service.form;

import lombok.Data;

@Data
public class EmailForm {

    private String para;
    private String titulo;
    private String conteudo;

    public EmailForm() {
    }

    public EmailForm(String para, String titulo, String conteudo) {
        this.para = para;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }
}
