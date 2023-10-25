package com.example.request;

import com.example.model.Livro;
import com.example.model.Sugestoes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class NovaSugestaoRequest {
    private List<String> titulo;
    private Livro livro;

    public Function<Livro, List<Sugestoes>> toModel(List<Sugestoes> sugestoes) {
        return livro -> {
            List<Sugestoes> sugestoesList = new ArrayList<>();
            sugestoes.forEach(row -> {
                Sugestoes sugestao = new Sugestoes(row.getTitulo(), livro);
                sugestoesList.add(sugestao);
            });
            return sugestoesList;
        };
    }
}
