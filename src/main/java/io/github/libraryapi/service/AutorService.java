package io.github.libraryapi.service;

import org.springframework.stereotype.Service;

import io.github.libraryapi.model.Autor;
import io.github.libraryapi.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository repository){
        this.autorRepository = repository;
    }

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

}
