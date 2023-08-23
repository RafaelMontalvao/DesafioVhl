package desafio.vhl.com.desafiovhl.dto;

import lombok.Data;

@Data
public class LivroResponse {

    private String isbn;

    private String titulo;

    private String autores;

    private Boolean disponivel;

}
