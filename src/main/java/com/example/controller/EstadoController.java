package com.example.controller;

import com.example.model.Estado;
import com.example.request.NovoEstadoRequest;
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
public class EstadoController {

    @PersistenceContext
    private final EntityManager entityManager;

    @PostMapping("api/v1/estados")
    @Transactional
    public ResponseEntity<?> criaEstado(@Valid @RequestBody NovoEstadoRequest request){
        Estado estado = request.toModel(entityManager);
        entityManager.persist(estado);
        return ResponseEntity.ok(estado);
    }
}
