package desafio.vhl.com.desafiovhl.service;

import desafio.vhl.com.desafiovhl.exception.RegistroExistenteException;
import desafio.vhl.com.desafiovhl.model.Livro;
import desafio.vhl.com.desafiovhl.model.Usuario;
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
}
