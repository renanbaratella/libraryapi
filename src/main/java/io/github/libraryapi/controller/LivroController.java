package io.github.libraryapi.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.libraryapi.controller.mappers.GenericController;
import io.github.libraryapi.controller.mappers.LivroMapper;
import io.github.libraryapi.dto.CadastroLivroDTO;
import io.github.libraryapi.dto.ResultadoPesquisaLivroDTO;
import io.github.libraryapi.model.Livro;
import io.github.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = livroService.salvar(mapper.toEntity(dto));
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).body(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("id") String id){
        return livroService.obterPorId(UUID.fromString(id))
            .map(livro -> {
                var dto = mapper.toDTO(livro);
                return ResponseEntity.ok(dto);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
