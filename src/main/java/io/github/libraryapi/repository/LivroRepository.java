package io.github.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.libraryapi.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, UUID>{

}
