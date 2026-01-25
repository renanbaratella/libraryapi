package io.github.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.libraryapi.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, UUID>{
}
