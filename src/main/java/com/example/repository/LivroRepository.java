package com.example.repository;

import com.example.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findAll();
    Optional<Livro> findById(Long id);
}
