package gerenciador_tarefas.service;

import gerenciador_tarefas.dto.UsuarioRequestDto;
import gerenciador_tarefas.model.Usuario;
import gerenciador_tarefas.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(UsuarioRequestDto usuarioDto) {
        if (usuarioRepository.findByUsername(usuarioDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username já existe.");
        }
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(usuarioDto.getUsername());
        novoUsuario.setEmail(usuarioDto.getEmail());
        novoUsuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        return usuarioRepository.save(novoUsuario);
    }
}