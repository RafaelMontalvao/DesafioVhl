package desafio.vhl.com.desafiovhl.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "EMPRESTIMOS")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String isbn;

    private Long cpf;

    private LocalDateTime dataHoraEmprestimo;

}
