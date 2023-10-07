package com.example.controller;

import com.example.model.Categoria;
import com.example.request.NovaCategoriaRequest;
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
public class CategoriaController {

    @PersistenceContext
    private final EntityManager entityManager;

    @PostMapping("/api/v1/categorias")
    @Transactional
    public ResponseEntity<?> cadastroCategoria(@RequestBody @Valid NovaCategoriaRequest request){
        Categoria categoria = new Categoria(request.getNome());
        entityManager.persist(categoria);
        return ResponseEntity.ok(categoria);
    }
}
