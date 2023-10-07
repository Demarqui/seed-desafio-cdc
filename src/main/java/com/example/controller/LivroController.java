package com.example.controller;

import com.example.request.NovoLivroRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
public class LivroController {

    @PersistenceContext
    private final EntityManager entityManager;
    @PostMapping("api/v1/livros")
    @Transactional
    public ResponseEntity<?> criaNovoLivro(@RequestBody @Valid NovoLivroRequest request){
        var livro = request.toModel(entityManager);
        entityManager.persist(livro);
        return ResponseEntity.ok(livro);
    }

}
