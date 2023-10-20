package com.example.request;

import com.example.model.Livro;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NovaSugestaoRequest {
    private List<String> titulo;
    private Livro livro;

    public NovaSugestaoRequest(List<String> titulo, @NotBlank Livro livro){
        this.titulo = titulo;
        this.livro = livro;
    }

//    public Function<Livro, List<Sugestoes>> toModel(){
//        return (livro) -> {
//            Sugestoes sugestoes = new Sugestoes(livro);
//            return sugestoes;
//        };
//    }
}
