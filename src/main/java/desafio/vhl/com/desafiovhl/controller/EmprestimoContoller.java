package desafio.vhl.com.desafiovhl.controller;


import desafio.vhl.com.desafiovhl.dto.EmprestimoRequest;
import desafio.vhl.com.desafiovhl.dto.EmprestimoResponse;
import desafio.vhl.com.desafiovhl.model.Emprestimo;
import desafio.vhl.com.desafiovhl.service.EmprestimoService;
import desafio.vhl.com.desafiovhl.service.LivroService;
import desafio.vhl.com.desafiovhl.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
@Slf4j
public class EmprestimoContoller {

    private final EmprestimoService emprestimoService;
    private final LivroService livroService;
    private final UsuarioService leitorService;

    private final ModelMapper mapper;


    @PostMapping
    public ResponseEntity<EmprestimoResponse> inserir(@RequestBody @Valid EmprestimoRequest request) {
        Emprestimo emprestimo = mapper.map(request, Emprestimo.class);
        emprestimo = emprestimoService.incluir(emprestimo);
        EmprestimoResponse resp = mapper.map(emprestimo, EmprestimoResponse.class);
        return ResponseEntity.created(URI.create(emprestimo.getId().toString())).body(resp);
    }
}
