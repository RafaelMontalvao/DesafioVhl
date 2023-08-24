package desafio.vhl.com.desafiovhl.controller;

import desafio.vhl.com.desafiovhl.dto.*;
import desafio.vhl.com.desafiovhl.model.Livro;
import desafio.vhl.com.desafiovhl.model.Usuario;
import desafio.vhl.com.desafiovhl.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@Slf4j
public class LivroController {

    private final LivroService livroService;

    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<LivroResponse>> consultar() {
        List<Livro> livros = livroService.consultar();
        List<LivroResponse> resp = livros.stream().map(l -> mapper.map(l, LivroResponse.class)).toList();
        return ResponseEntity.ok(resp);
    }
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<LivroResponse>> consultarPorAutor(@PathVariable("autor") String autor) {
        List<Livro>livros = livroService.consultarPorAutor(autor);
        Collections.sort(livros, Comparator.comparing(Livro::getAutores));
        List<LivroResponse> resp = livros.stream().map(l -> mapper.map(l, LivroResponse.class)).toList();
        return ResponseEntity.ok(resp);
    }
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroResponse>> consultarPorTitulo(@PathVariable("titulo") String titulo) {
        List<Livro>livros = livroService.consultarPortitulo(titulo);
        Collections.sort(livros, Comparator.comparing(Livro::getTitulo));
        List<LivroResponse> resp = livros.stream().map(l -> mapper.map(l, LivroResponse.class)).toList();
        return ResponseEntity.ok(resp);
    }
    
    
    @PostMapping
    public ResponseEntity<LivroResponse> inserir(@RequestBody @Valid LivroRequest request) {
        Livro livro = mapper.map(request, Livro.class);
        livro = livroService.criar(livro);
        LivroResponse resp = mapper.map(livro, LivroResponse.class);
        return ResponseEntity.created(URI.create(livro.getIsbn().toString())).body(resp);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<LivroResponse> atualizar(@PathVariable String isbn, @RequestBody @Valid LivroRequestEdicao request) {
        Livro livroExistente = livroService.buscarPorIsbn(isbn);
            if (livroExistente == null) {
                return ResponseEntity.notFound().build();
        }
        mapper.map(request, livroExistente); // Atualiza os dados do usuário existente
        livroExistente = livroService.editar(livroExistente); // Salva as alterações
        LivroResponse resp = mapper.map(livroExistente, LivroResponse.class);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity excluir(@PathVariable String isbn) {
        livroService.excluir(isbn);
        return ResponseEntity.noContent().build();
    }

}