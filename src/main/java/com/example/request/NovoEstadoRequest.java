package com.example.request;

import com.example.model.Estado;
import com.example.model.Pais;
import com.example.validator.ExistsById;
import com.example.validator.UniqueValue;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;
    @NotNull
    @ExistsById(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    public Estado toModel(EntityManager entityManager){
        return new Estado(this.nome, entityManager.find(Pais.class, idPais));
    }
}
