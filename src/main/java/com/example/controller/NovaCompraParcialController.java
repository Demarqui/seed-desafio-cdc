package com.example.controller;

import com.example.request.NovaCompraParcialRequest;
import com.example.validator.EstadoPertenceAPaisValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NovaCompraParcialController {

    private final EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(estadoPertenceAPaisValidator);
    }
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("api/v1/compraParcial")
    public ResponseEntity<?> criaCompraParcial(@Valid @RequestBody NovaCompraParcialRequest request){
        var novaCompraParcialGerada = request.toModel(entityManager);
        return ResponseEntity.ok(novaCompraParcialGerada.toString());
    }
}
