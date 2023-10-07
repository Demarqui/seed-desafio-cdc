package com.example.request;

import com.example.model.Autor;
import com.example.validator.UniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NovoAutorRequest {
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    @UniqueValue(domainClass = Autor.class, fieldName = "email")
    private String email;
    @NotBlank
    @Size(max = 400)
    private String descricao;

    public NovoAutorRequest(@NotBlank String nome,
                            @NotBlank @Email String email,
                            @NotBlank String descricao) {
        super();
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toAutor(){
        return new Autor(this.nome, this.email, this.descricao);
    }
}
