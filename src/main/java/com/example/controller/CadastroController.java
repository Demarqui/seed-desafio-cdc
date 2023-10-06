package com.example.controller;

import com.example.model.Autor;
import com.example.request.NovoAutorRequest;
import com.example.validator.ProibeEmailDuplicadoAutorValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CadastroController {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeEmailDuplicadoAutorValidator);
    }
    @PostMapping("/api/v1/autores")
    @Transactional
    public ResponseEntity<?> cadastroAutor(@RequestBody @Valid NovoAutorRequest novoAutorRequest){
        var autorEntity = novoAutorRequest.toAutor();
        Optional.ofNullable(entityManager.createQuery("select * from tb_autor a " +
                "where a.email = " + autorEntity.getEmail())).orElseThrow();
        entityManager.persist(autorEntity);
        return ResponseEntity.ok(autorEntity);
    }
}
