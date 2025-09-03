package com.example.gerenciadortarefas.service; 

import com.example.gerenciadortarefas.dto.TarefaDto; 
import com.example.gerenciadortarefas.model.Tarefa; 
import com.example.gerenciadortarefas.model.Usuario; 
import com.example.gerenciadortarefas.repository.TarefaRepository; 
import com.example.gerenciadortarefas.repository.UsuarioRepository; 
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    public TarefaService(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TarefaDto criarTarefa(String username, TarefaDto tarefaDto) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo(tarefaDto.getTitulo());
        novaTarefa.setDescricao(tarefaDto.getDescricao());
        novaTarefa.setStatus(tarefaDto.getStatus());
        novaTarefa.setUsuario(usuario);
        Tarefa tarefaSalva = tarefaRepository.save(novaTarefa);
        return mapToDto(tarefaSalva);
    }

    public List<TarefaDto> listarTarefasPorUsuario(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return tarefaRepository.findByUsuarioId(usuario.getId()).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TarefaDto mapToDto(Tarefa tarefa) {
        TarefaDto dto = new TarefaDto();
        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setStatus(tarefa.getStatus());
        dto.setDataDeCriacao(tarefa.getDataDeCriacao());
        return dto;
    }
}