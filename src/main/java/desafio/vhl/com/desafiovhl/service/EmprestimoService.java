package desafio.vhl.com.desafiovhl.service;


import desafio.vhl.com.desafiovhl.exception.LivroIndisponivelException;
import desafio.vhl.com.desafiovhl.model.Emprestimo;
import desafio.vhl.com.desafiovhl.model.Livro;
import desafio.vhl.com.desafiovhl.model.Usuario;
import desafio.vhl.com.desafiovhl.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private final EmprestimoRepository emprestimoRepo;


  @Transactional
    public Emprestimo incluir(Emprestimo emprestimo) {
        Livro livro = livroService.consultar(emprestimo.getIsbn());
        if (!livro.isDisponivel())
            throw new LivroIndisponivelException();
        emprestimo.setDataHoraEmprestimo(LocalDateTime.now());
        emprestimo = emprestimoRepo.save(emprestimo);
        livroService.indisponibilizar(emprestimo.getIsbn());
        usuarioService.incrementarEmprestimosAtivos(emprestimo.getCpf());
        return emprestimo;
    }
    }



