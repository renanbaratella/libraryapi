package io.github.libraryapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.github.libraryapi.model.Autor;
import io.github.libraryapi.repository.AutorRepository;
import io.github.libraryapi.validator.AutorValidator;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator validator;

    public AutorService(AutorRepository repository, AutorValidator validator){
        this.autorRepository = repository;
        this.validator = validator;
    }

    public Autor salvar(Autor autor){
        validator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor){
        if(autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar é necessário que o autor esteja salvo na base");
        }

        validator.validar(autor);

        autorRepository.save(autor);
    }

    public Optional<Autor> getById(UUID id){
        return autorRepository.findById(id);
    }

    public void deleteById(Autor autor) {
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        if(nome != null && nacionalidade != null){
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if(nome != null){
            return autorRepository.findByNome(nome);
        }

        if(nacionalidade != null){
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();
    }

}
