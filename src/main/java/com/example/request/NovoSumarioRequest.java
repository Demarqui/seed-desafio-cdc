package com.example.request;

import com.example.model.Livro;
import com.example.model.Sumario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class NovoSumarioRequest {

    private Long id;
    private String descricao;
    private List<String> detalhes;
    private Livro livro;

    public Function<Livro, List<Sumario>> toModel(List<Sumario> sumarios) {
        return livro -> {
            List<Sumario> sumariosList = new ArrayList<>();
            sumarios.forEach(row -> {
                Sumario sum = new Sumario(row.getId(), livro, row.getDescricao(), row.getDetalhes());
                sumariosList.add(sum);
            });
            return sumariosList;
        };
    }
}
