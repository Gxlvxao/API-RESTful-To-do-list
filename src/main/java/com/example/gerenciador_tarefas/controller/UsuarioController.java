package com.example.gerenciadortarefas.controller;

import com.example.gerenciadortarefas.dto.UsuarioRequestDto;
import com.example.gerenciadortarefas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@Valid @RequestBody UsuarioRequestDto usuarioDto) {
        usuarioService.criarUsuario(usuarioDto);
        return new ResponseEntity<>("Usuário registrado com sucesso!", HttpStatus.CREATED);
    }
}