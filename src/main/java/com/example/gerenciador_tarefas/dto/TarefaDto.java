package gerenciador_tarefas.dto;

import gerenciador_tarefas.model.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TarefaDto {
    private Long id;
    @NotBlank
    private String titulo;
    private String descricao;
    @NotNull
    private StatusTarefa status;
    private LocalDateTime dataDeCriacao;
}