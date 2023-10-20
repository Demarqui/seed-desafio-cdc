package com.example.controller;

import com.example.model.Livro;
import com.example.repository.LivroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DetalheLivrosController {

    private final LivroRepository repository;
    @GetMapping("api/v1/livros")
    @Transactional
    public List<Livro> getLivros(){
        List<Livro> todos = repository.findAll();
        return todos.stream().map(Livro::toModel).collect(Collectors.toList());//4
    }

    @GetMapping("api/v1/livro/{id}")
    @Transactional
    public ResponseEntity<?> getDetalhesLivro(@PathVariable Long id) throws Exception {
        Optional<Livro> livro = repository.findById(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get().buildDetalhes());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
