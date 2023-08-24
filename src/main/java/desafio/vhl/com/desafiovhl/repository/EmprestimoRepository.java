package desafio.vhl.com.desafiovhl.repository;

import desafio.vhl.com.desafiovhl.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository  extends JpaRepository<Emprestimo, Integer> {
}
