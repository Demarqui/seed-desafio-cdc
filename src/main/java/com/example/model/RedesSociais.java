package com.example.model;

import com.example.enums.RedesSociaisEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_redes_sociais")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedesSociais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RedesSociaisEnum redeSocial;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    @JsonIgnore
    private Livro livro;
}
