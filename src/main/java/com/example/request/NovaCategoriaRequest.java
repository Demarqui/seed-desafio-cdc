package com.example.request;

import com.example.model.Categoria;
import com.example.validator.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    public NovaCategoriaRequest(@NotBlank String nome){
        this.nome = nome;
    }
}
