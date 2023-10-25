package com.example.controller;

import com.example.request.NovoLivroRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LivroController {

    @PersistenceContext
    private final EntityManager entityManager;

    //1
    @PostMapping("api/v1/livros")
    @Transactional
    public ResponseEntity<?> criaNovoLivro(@RequestBody @Valid NovoLivroRequest request){//2
        var livro = request.toModel(entityManager); //3
        entityManager.persist(livro);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
