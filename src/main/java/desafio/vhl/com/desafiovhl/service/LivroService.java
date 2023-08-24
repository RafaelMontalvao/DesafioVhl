package desafio.vhl.com.desafiovhl.service;
import desafio.vhl.com.desafiovhl.exception.RegistroExistenteException;
import desafio.vhl.com.desafiovhl.exception.RegistroNaoEncontradoException;
import desafio.vhl.com.desafiovhl.model.Livro;
import desafio.vhl.com.desafiovhl.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepo;

    public List<Livro> consultar() {
        return livroRepo.findAll();
    }
    public Livro consultar(String isbn) {
        return livroRepo.findById(isbn)
                .orElseThrow(RegistroNaoEncontradoException::new);
    }

    public List<Livro> consultarPorAutor(String autor) {
        return livroRepo.findByAutoresContainsIgnoreCase(autor);
    }

    public List<Livro> consultarPortitulo(String titulo) {
        return livroRepo.findByTituloContainsIgnoreCase(titulo);
    }

    public Livro buscarPorIsbn(String isbn) {
        return livroRepo.findById(isbn).orElse(null);
    }

    public Livro criar(Livro livro) {
        if (livroRepo.existsById(livro.getIsbn()))
            throw new RegistroExistenteException();
        livro = livroRepo.save(livro);
        return livro;
    }

    public Livro editar(Livro livro) {
        return livroRepo.save(livro);
    }

    public void excluir(String isbn) {
        Livro livro = this.buscarPorIsbn(isbn);
        if (livro == null) {
            throw new RegistroNaoEncontradoException();
        }
        livroRepo.deleteById(isbn);
    }
    public void indisponibilizar(String isbn) {
        Livro livro = this.consultar(isbn);
        livro.setDisponivel(false);
        livroRepo.save(livro);
    }
    public void disponibilizar(String isbn) {
        Livro livro = this.consultar(isbn);
        livro.setDisponivel(true);
        livroRepo.save(livro);
    }
}

