package br.com.pepper.godzillalocalfilmes.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pepper.godzillalocalfilmes.config.security.TokenService;
import br.com.pepper.godzillalocalfilmes.controllers.dto.TokenDto;
import br.com.pepper.godzillalocalfilmes.form.LoginForm;
import br.com.pepper.godzillalocalfilmes.form.RegistroForm;
import br.com.pepper.godzillalocalfilmes.models.Usuario;
import br.com.pepper.godzillalocalfilmes.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/usuario")
    public void novoUsuario(@RequestBody @Valid RegistroForm form) {
        Usuario usuario = form.converter();
        usuarioRepository.save(usuario);
    }
}