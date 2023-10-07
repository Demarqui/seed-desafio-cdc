package com.example.controller;

import com.example.model.Livro;
import com.example.repository.LivroRepository;
import com.example.request.NovoLivroRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LivroController {

    @PersistenceContext
    private final EntityManager entityManager;

    //1
    private final LivroRepository repository;
    @PostMapping("api/v1/livros")
    @Transactional
    public ResponseEntity<?> criaNovoLivro(@RequestBody @Valid NovoLivroRequest request){
        var livro = request.toModel(entityManager);
        entityManager.persist(livro);
        return ResponseEntity.ok(livro);
    }

    //2
    @GetMapping("api/v1/livros")
    @Transactional
    public List<Livro> getLivros(){
        List<Livro> todos = repository.findAll();
        return todos.stream().map(Livro::toModel).collect(Collectors.toList());
    }

}
