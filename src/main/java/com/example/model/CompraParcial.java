package com.example.model;

import com.example.validator.ExistsById;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompraParcial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    private String documento;
    @NotBlank
    private String endereco;
    @NotNull
    private String telefone;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    @ExistsById(domainClass = Pais.class, fieldName = "id")
    private Long idPais;
    @NotNull
    @ExistsById(domainClass = Estado.class, fieldName = "id")
    private Long idEstado;
    @NotBlank
    private String cep;
    public CompraParcial(String email, String nome, String sobrenome, String cpfCnpj,
                         String endereco, String documento, String telefone, String complemento,
                         String cidade, Long idPais, Long idEstado, String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.cep = cep;
    }
}
