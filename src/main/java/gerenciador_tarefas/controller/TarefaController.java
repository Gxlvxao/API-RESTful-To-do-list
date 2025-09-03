package com.example.gerenciadortarefas.controller;

import com.example.gerenciadortarefas.dto.TarefaDto;
import com.example.gerenciadortarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaDto> criarTarefa(Principal principal, @Valid @RequestBody TarefaDto tarefaDto) {
        TarefaDto novaTarefa = tarefaService.criarTarefa(principal.getName(), tarefaDto);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TarefaDto>> listarMinhasTarefas(Principal principal) {
        List<TarefaDto> tarefas = tarefaService.listarTarefasPorUsuario(principal.getName());
        return ResponseEntity.ok(tarefas);
    }
}