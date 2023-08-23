package desafio.vhl.com.desafiovhl.repository;
import desafio.vhl.com.desafiovhl.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

      List<Usuario> findByNomeContaining(String nome);

}
