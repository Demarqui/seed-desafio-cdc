package com.example.enums;

import lombok.Getter;

@Getter
public enum TipoLivroEnum {

    EBOOK("E-book*", "(.pdf, .epub)"),
    IMPRESSO("Impresso", ""),
    EBOOK_IMPRESSO("E-book + Impresso", "");

    private final String descricao;
    private final String detalhe;

    TipoLivroEnum(String descricao, String detalhe){
        this.descricao = descricao;
        this.detalhe = detalhe;
    }
}
