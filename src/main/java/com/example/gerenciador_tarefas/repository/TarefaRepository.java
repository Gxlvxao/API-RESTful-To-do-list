package com.example.gerenciadortarefas.repository;

import com.example.gerenciadortarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuarioId(Long usuarioId);
}