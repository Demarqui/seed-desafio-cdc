package com.example.request;

import com.example.model.Autor;
import com.example.model.Categoria;
import com.example.model.Livro;
import com.example.validator.ExistsById;
import com.example.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
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
    public Livro toModel(EntityManager entityManager){
        Autor autor = entityManager.find(Autor.class, idAutor);
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);

        Assert.state(autor!=null, "O autor informado não existe");
        Assert.state(categoria!=null, "A categoria informada não existe");

        return new Livro(titulo, resumo, sumario, preco, numPaginas, isbn, dataLancamento,
                categoria, autor);
    }
}
