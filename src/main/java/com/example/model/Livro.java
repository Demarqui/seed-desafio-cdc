package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_livro")
@ToString
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;
    private int numPaginas;
    private String isbn;

    private LocalDate dataLancamento;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn
    private Categoria categoria;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn
    private Autor autor;

    public Livro(String titulo, String resumo, String sumario, BigDecimal preco, int numPaginas, String isbn, LocalDate dataLancamento, Categoria categoria, Autor autor) {
        super();
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPaginas = numPaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.categoria = categoria;
        this.autor = autor;
    }
}
