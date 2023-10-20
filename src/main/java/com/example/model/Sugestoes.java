package com.example.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "tb_sugestoes")
@Getter
@Setter
public class Sugestoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<String> titulo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    public Sugestoes(Livro livro){
        this.livro = livro;
        this.titulo = Collections.singletonList(livro.getTitulo());
    }
}
