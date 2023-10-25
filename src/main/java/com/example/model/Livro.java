package com.example.model;

import com.example.response.DetalhesLivroResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_livro")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;
    private Integer numPaginas;
    private String isbn;

    private LocalDate dataLancamento;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.PERSIST)
    private List<TipoLivro> tipoLivro;

    private String sobreAtualizacoes;

    private String conteudo;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.PERSIST)
    private List<Sumario> sumarios;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.PERSIST)
    private List<RedesSociais> redesSociais;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.PERSIST)
    private List<Sugestoes> sugestoes;

    public Livro(String titulo, String resumo, String sumario, BigDecimal preco, Integer numPaginas, String isbn,
                 LocalDate dataLancamento, Categoria categoria, Autor autor,
                 String sobreAtualizacoes, String conteudo,
                 Function<Livro, List<Sugestoes>> funcaoCriadoraSugestoes,
                 Function<Livro, List<TipoLivro>> funcaoCriadoraTipoLivro,
                 Function<Livro, List<Sumario>> funcaoCriadoraSumarios,
                 Function<Livro, List<RedesSociais>> funcaoCriadoraRedesSociais
    ) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPaginas = numPaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
        this.categoria = categoria;
        this.autor = autor;
        this.sobreAtualizacoes = sobreAtualizacoes;
        this.conteudo = conteudo;
        this.sugestoes = funcaoCriadoraSugestoes.apply(this);
        this.tipoLivro = funcaoCriadoraTipoLivro.apply(this);
        this.sumarios = funcaoCriadoraSumarios.apply(this);
        this.redesSociais = funcaoCriadoraRedesSociais.apply(this);
    }

    public Livro(Long id, String titulo){
        this.id = id;
        this.titulo = titulo;
    }

    public Livro(Integer numPaginas, String isbn, LocalDate dataLancamento){
        this.numPaginas = numPaginas;
        this.isbn = isbn;
        this.dataLancamento = dataLancamento;
    }

    public static Livro toModel(Livro livro){
       return new Livro(livro.id, livro.titulo);
    }

    public DetalhesLivroResponse buildDetalhes(){
        return DetalhesLivroResponse.builder()
                .titulo(titulo)
                .nomeAutor(autor.getNome())
                .detalhesLivros(tipoLivro.stream().map(livro ->
                        livro.getTipoLivro().getDescricao() +
                        livro.getTipoLivro().getDetalhe() + " " + livro.getPreco()).toList())
                .sobreAtualizacoes(sobreAtualizacoes)
                .conteudo(conteudo)
                .sumarios(sumarios)
                .autor(new Autor(autor.getNome(), autor.getDescricao()))
                .compartilhe(redesSociais.stream().map(x -> "Compartilhe no " +
                        x.getRedeSocial().getNome()).collect(Collectors.toList()))
                .dadosDoProduto(new Livro(numPaginas, isbn, dataLancamento))
                .sugestoes(sugestoes.stream().map(sugestao ->
                        String.join("+", sugestao.getTitulo())).toList())
                .build();
    }
}
