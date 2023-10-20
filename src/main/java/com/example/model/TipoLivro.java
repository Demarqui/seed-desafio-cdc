package com.example.model;

import com.example.enums.TipoLivroEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Entity
@Table(name = "tb_tipo_livro")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoLivroEnum tipoLivro;

    @Min(10)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    public TipoLivro(Livro livro) {
        this.livro = livro;
    }

    public Function<Livro, List<TipoLivro>> toModel(){
        List<TipoLivro> tipoLivros = new ArrayList<>();
        return liv -> {
            TipoLivro tipoLivro = new TipoLivro(liv);
            tipoLivros.add(tipoLivro);
            return tipoLivros;
        };
    }
}
