package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Entity
@Table(name = "tb_autor")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(max = 400)
    private String descricao;

    private LocalDateTime dataCadastro;

    @Deprecated
    public Autor(){

    }
    public Autor(@NotBlank String nome, @NotBlank @Email String email, @NonNull String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.dataCadastro = LocalDateTime.now();
    }

    public Autor(@NotBlank String nome, @NotBlank String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }
}
