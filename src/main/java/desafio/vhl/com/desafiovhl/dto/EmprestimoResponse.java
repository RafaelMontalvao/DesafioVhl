package desafio.vhl.com.desafiovhl.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmprestimoResponse {

    private Integer id;

    private String isbn;

    private String titulo;

    private Long cpf;

    private String nome;

    private LocalDateTime dataHoraEmprestimo;

}
