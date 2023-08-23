package desafio.vhl.com.desafiovhl.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LivroRequest {


    @NotEmpty(message = "Campo obrigatório")
    private String isbn;

    @NotEmpty(message = "Campo obrigatório")
    private String titulo;

    @NotEmpty(message = "Campo obrigatório")
    private String autores;

    private boolean disponivel;

}


