package com.example.controller;

import com.example.model.Pais;
import com.example.request.NovoPaisRequest;
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
public class PaisController {
    @PersistenceContext
    private final EntityManager entityManager;

    @PostMapping("api/v1/pais")
    @Transactional
    public ResponseEntity<?> criaPais(@Valid @RequestBody NovoPaisRequest request){
        Pais pais = new Pais(request.getNome());
        entityManager.persist(pais);
        return ResponseEntity.ok(pais);
    }
}
