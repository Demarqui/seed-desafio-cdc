package com.example.request;

import com.example.model.CompraParcial;
import com.example.validator.Documento;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NovaCompraParcialRequest {

    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @Documento
    @NotBlank
    private String documento;
    @NotBlank
    private String telefone;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    private Long idPais;
    @NotNull
    private Long idEstado;
    @NotBlank
    private String cep;

    public CompraParcial toModel(EntityManager entityManager){
        return new CompraParcial(email, nome, sobrenome, documento, endereco, endereco, telefone,
                complemento, cidade, idPais, idEstado, cep);
    }
}
