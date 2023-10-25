package com.example.request;

import com.example.enums.TipoLivroEnum;
import com.example.model.Livro;
import com.example.model.TipoLivro;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class NovoTipoLivroRequest {

    private Long id;
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    private TipoLivroEnum tipoLivroEnum;
    @NotBlank
    private Livro livro;

    public Function<Livro, List<TipoLivro>> toModel(List<TipoLivro> tiposLivro){
        List<TipoLivro> listaTipoLivro = new ArrayList<>();
        return livro -> {
            tiposLivro.forEach(row -> {
                TipoLivro tipoLivro = new TipoLivro(row.getId(), row.getTipoLivro(),
                        row.getPreco(), livro);
                listaTipoLivro.add(tipoLivro);
            });
            return listaTipoLivro;
        };
    }

}
