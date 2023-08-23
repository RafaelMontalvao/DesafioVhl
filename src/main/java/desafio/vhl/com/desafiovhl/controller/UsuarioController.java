package desafio.vhl.com.desafiovhl.controller;


import desafio.vhl.com.desafiovhl.dto.UsuarioResponse;
import desafio.vhl.com.desafiovhl.model.Usuario;
import desafio.vhl.com.desafiovhl.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController {

    @Autowired
    private ModelMapper mapper;

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> consultar() {
        List<Usuario> usuarios = usuarioService.consultar();
        Collections.sort(usuarios, Comparator.comparing(Usuario::getNome)); // ordem alfabetica
        List<UsuarioResponse> resp = usuarios.stream().map(l -> mapper.map(l, UsuarioResponse.class)).toList();
        return ResponseEntity.ok(resp);
    }

}
