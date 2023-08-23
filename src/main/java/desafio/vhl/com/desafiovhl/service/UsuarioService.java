package desafio.vhl.com.desafiovhl.service;


import desafio.vhl.com.desafiovhl.controller.UsuarioController;

import desafio.vhl.com.desafiovhl.exception.LeitorComEmprestimosException;
import desafio.vhl.com.desafiovhl.exception.RegistroExistenteException;
import desafio.vhl.com.desafiovhl.exception.RegistroNaoEncontradoException;
import desafio.vhl.com.desafiovhl.model.Usuario;
import desafio.vhl.com.desafiovhl.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;



    public  List<Usuario> consultar() {
        return usuarioRepo.findAll();
    }

    public List<Usuario> consultarPorNome(String nome) {
        return usuarioRepo.findByNomeContaining(nome);
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

    public void excluir(Long cpf) {
        Usuario usuario = this.buscarPorCpf(cpf);
        if (usuario == null){
            throw new RegistroNaoEncontradoException();
        }
        boolean possuiEmprestimosAtivos = usuario.getQtdEmprestimos() > 0;
        if (possuiEmprestimosAtivos)
            throw new LeitorComEmprestimosException();
        usuarioRepo.deleteById(cpf);
    }
}
