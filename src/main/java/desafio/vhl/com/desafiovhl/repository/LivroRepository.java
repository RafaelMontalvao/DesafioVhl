package desafio.vhl.com.desafiovhl.repository;

import desafio.vhl.com.desafiovhl.model.Livro;
import desafio.vhl.com.desafiovhl.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String> {

    Optional<Livro> findByIsbn(String isbn);
    List<Livro>findByAutoresContainsIgnoreCase(String autores);
    List<Livro>findByTituloContainsIgnoreCase(String titulo);
    Optional<Livro> findByAutores(String autores);
}
