package br.com.pepper.godzillalocalfilmes.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.pepper.godzillalocalfilmes.models.Usuario;
import br.com.pepper.godzillalocalfilmes.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuariorRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuariorRepository) {
        this.tokenService = tokenService;
        this.usuariorRepository = usuariorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if (valido) {
            auntenticarUsuario(token);
        }

        filterChain.doFilter(request, response);

    }

    private void auntenticarUsuario(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuariorRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

}