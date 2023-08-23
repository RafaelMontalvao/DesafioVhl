package desafio.vhl.com.desafiovhl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "USUARIOS")
@Data
public class Usuario {

    @Id
    private Long cpf;

    private String nome;

    private LocalDate dataNascimento;

    private int qtdEmprestimos;
}
