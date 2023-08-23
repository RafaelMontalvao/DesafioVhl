package desafio.vhl.com.desafiovhl.service;


import desafio.vhl.com.desafiovhl.dto.UsuarioResponse;
import desafio.vhl.com.desafiovhl.exception.RegistroExistenteException;
import desafio.vhl.com.desafiovhl.model.Usuario;
import desafio.vhl.com.desafiovhl.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {


    private final UsuarioRepository usuarioRepo;

    public  List<Usuario> consultar() {
        return usuarioRepo.findAll();
    }

    public Usuario editar(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }
   public Usuario buscarPorCpf(Long cpf) {
        return usuarioRepo.findById(cpf).orElse(null);
    }


    public Usuario criar(Usuario usuario) {
        if (usuarioRepo.existsById(usuario.getCpf()))
            throw new RegistroExistenteException();
        usuario = usuarioRepo.save(usuario);
        return usuario;
    }
}
