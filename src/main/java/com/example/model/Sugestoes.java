package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tb_sugestoes")
@Getter
@Setter
@NoArgsConstructor
public class Sugestoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<String> titulo;
    @ManyToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    @JsonIgnore
    private Livro livro;

    public Sugestoes(List<String> titulo, Livro livro){
        this.livro = livro;
        this.titulo = titulo;
    }
}
