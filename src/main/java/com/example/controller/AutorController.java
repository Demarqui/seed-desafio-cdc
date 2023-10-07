package com.example.controller;

import com.example.request.NovoAutorRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutorController {
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/api/v1/autores")
    @Transactional
    public ResponseEntity<?> cadastroAutor(@RequestBody @Valid NovoAutorRequest novoAutorRequest){
        var autorEntity = novoAutorRequest.toAutor();
        entityManager.persist(autorEntity);
        return ResponseEntity.ok(autorEntity);
    }
}
