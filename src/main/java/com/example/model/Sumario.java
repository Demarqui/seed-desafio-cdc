package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_sumario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sumario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private String descricao;

    private List<String> detalhes;

}
