package com.example.response;

import com.example.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DetalhesLivroResponse {

    private String titulo;
    private String nomeAutor;
    private List<String> detalhesLivros;
    private String sobreAtualizacoes;
    private String conteudo;
    private List<Sumario> sumarios;
    private Autor autor;
    private Livro dadosDoProduto;
    private List<String> compartilhe;
    private List<String> sugestoes;

}
