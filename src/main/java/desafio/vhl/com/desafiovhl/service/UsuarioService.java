package desafio.vhl.com.desafiovhl.service;


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
}}
