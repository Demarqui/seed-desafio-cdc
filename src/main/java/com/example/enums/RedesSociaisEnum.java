package com.example.enums;

import lombok.Getter;

@Getter
public enum RedesSociaisEnum {

    FACEBOOK("Facebook"),
    TWITTER("Twitter");

    private final String nome;

    RedesSociaisEnum(String nome){
        this.nome = nome;
    }
}
