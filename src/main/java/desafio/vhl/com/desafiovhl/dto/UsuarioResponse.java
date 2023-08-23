package desafio.vhl.com.desafiovhl.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioResponse {

    private Long cpf;

    private String nome;

    private LocalDate dataNascimento;

    private int qtdEmprestimos;
}
