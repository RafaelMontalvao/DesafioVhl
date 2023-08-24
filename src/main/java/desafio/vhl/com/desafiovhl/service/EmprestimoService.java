package desafio.vhl.com.desafiovhl.service;
import desafio.vhl.com.desafiovhl.exception.LeitorComMuitosEmprestimosException;
import desafio.vhl.com.desafiovhl.exception.LivroIndisponivelException;
import desafio.vhl.com.desafiovhl.exception.RegistroNaoEncontradoException;
import desafio.vhl.com.desafiovhl.model.Emprestimo;
import desafio.vhl.com.desafiovhl.model.Livro;
import desafio.vhl.com.desafiovhl.model.Usuario;
import desafio.vhl.com.desafiovhl.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private final EmprestimoRepository emprestimoRepo;
    public List<Emprestimo> consultar() {
        return emprestimoRepo.findAll();
    }
    public Emprestimo consultar(Integer id) {
        return emprestimoRepo.findById(id)
                .orElseThrow(RegistroNaoEncontradoException::new);
    }
    @Transactional
    public Emprestimo incluir(Emprestimo emprestimo) {
        Livro livro = livroService.consultar(emprestimo.getIsbn());
        Usuario usuario = usuarioService.consultar(emprestimo.getCpf());
        if(usuario.getQtdEmprestimos()>=2)
            throw new LeitorComMuitosEmprestimosException();
        if (!livro.isDisponivel())
            throw new LivroIndisponivelException();
        emprestimo.setDataHoraEmprestimo(LocalDateTime.now());
        emprestimo = emprestimoRepo.save(emprestimo);
        livroService.indisponibilizar(emprestimo.getIsbn());
        usuarioService.incrementarEmprestimosAtivos(emprestimo.getCpf());
        return emprestimo;
    }
    public void excluir(Integer id) {
        Emprestimo emprestimo = this.consultar(id);
        livroService.disponibilizar(emprestimo.getIsbn());
        usuarioService.decrementarEmprestimosAtivos(emprestimo.getCpf());
        emprestimoRepo.deleteById(id);
    }
}



