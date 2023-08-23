package desafio.vhl.com.desafiovhl.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "LIVROS")
public class Livro {

    @Id
    private String isbn;

    private String titulo;

    private String autores;

    private boolean disponivel;
}
