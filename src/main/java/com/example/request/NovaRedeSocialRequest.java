package com.example.request;

import com.example.enums.RedesSociaisEnum;
import com.example.model.Livro;
import com.example.model.RedesSociais;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
public class NovaRedeSocialRequest {

    private Long id;
    @Enumerated(EnumType.STRING)
    private RedesSociaisEnum redesSociaisEnum;
    private Livro livro;

    public Function<Livro, List<RedesSociais>> toModel(List<RedesSociais> redesSociaisList){
        return livro -> {
            List<RedesSociais> redesSociais = new ArrayList<>();
            redesSociaisList.forEach(row -> {
                RedesSociais redeSocial = new RedesSociais(row.getId(),
                        row.getRedeSocial(), livro);
                redesSociais.add(redeSocial);
            });
            return redesSociais;
        };
    }
}
