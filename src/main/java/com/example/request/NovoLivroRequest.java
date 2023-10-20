package com.example.request;

import com.example.model.*;
import com.example.validator.ExistsById;
import com.example.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovoLivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;
    @NotNull
    @Min(value = 20)
    private BigDecimal preco;
    @NotNull
    @Min(value = 100)
    private Integer numPaginas;
    @NotNull
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;
    @NotNull
    @ExistsById(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @NotNull
    @ExistsById(domainClass = Autor.class, fieldName = "id")
    private Long idAutor;

    @NotNull
    @Size(min = 1)
    private List<TipoLivro> tipoLivro;

    @NotBlank
    private String sobreAtualizacoes;

    @NotBlank
    private String conteudo;

    @NotNull
    @Size(min = 2)
    private List<Sumario> sumarios;

    @NotNull
    @Size(min = 1)
    private List<RedesSociais> redesSociais;

    @Nullable
    private List<Sugestoes> sugestoes;


    public Livro toModel(EntityManager entityManager){
        Autor autor = entityManager.find(Autor.class, idAutor);
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);

        Assert.state(autor!=null, "O autor informado não existe");
        Assert.state(categoria!=null, "A categoria informada não existe");

        Livro livro = new Livro(titulo, resumo, sumario, preco, numPaginas, isbn, dataLancamento,
                categoria, autor, tipoLivro, sobreAtualizacoes, conteudo, sumarios, redesSociais, sugestoes);

        sumarios.forEach(sumario -> sumario.setLivro(livro));
        redesSociais.forEach(redeSocial -> redeSocial.setLivro(livro));
        sugestoes.forEach(sugestao -> sugestao.setLivro(livro));
        tipoLivro.forEach(tipoLivro -> tipoLivro.setLivro(livro));
        entityManager.persist(livro);
        return livro;
    }
}
