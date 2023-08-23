package desafio.vhl.com.desafiovhl.service;

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
}
