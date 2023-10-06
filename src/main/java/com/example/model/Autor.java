package com.example.model;

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
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NonNull
    @Size(max = 400)
    private String descricao;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Deprecated
    public Autor(){

    }
    public Autor(@NotBlank String nome, @NotBlank @Email String email, @NonNull String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }
}
