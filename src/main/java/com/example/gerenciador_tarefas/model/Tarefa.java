package com.example.gerenciadortarefas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
@Data
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;
    @Column(updatable = false)
    private LocalDateTime dataDeCriacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @PrePersist
    protected void onCreate() {
        this.dataDeCriacao = LocalDateTime.now();
    }
}