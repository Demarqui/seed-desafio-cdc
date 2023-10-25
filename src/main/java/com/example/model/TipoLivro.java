package com.example.model;

import com.example.enums.TipoLivroEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    @JsonIgnore
    private Livro livro;
}
