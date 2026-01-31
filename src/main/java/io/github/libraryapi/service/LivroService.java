package io.github.libraryapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.libraryapi.model.GeneroLivro;
import io.github.libraryapi.model.Livro;
import io.github.libraryapi.repository.LivroRepository;
import io.github.libraryapi.repository.specs.LivroSpecs;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {


    private final LivroRepository livroRepository;

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro){
        livroRepository.delete(livro);
    }

    public List<Livro> pesquisa(String isbn, String titulo, GeneroLivro genero, Integer anoPublicacao){
        Specification<Livro> specs = Specification
            .where(LivroSpecs.isbnEqual(isbn))
            .and(LivroSpecs.tituloLike(titulo))
            .and(LivroSpecs.generoEqual(genero));
            
        return livroRepository.findAll(specs);
    }

}
