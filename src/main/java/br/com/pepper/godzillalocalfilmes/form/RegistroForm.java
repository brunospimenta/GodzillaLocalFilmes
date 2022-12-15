package br.com.pepper.godzillalocalfilmes.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.pepper.godzillalocalfilmes.models.Usuario;

public class RegistroForm {

    private String email;
    private String senha;

    public RegistroForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario converter() {
        Usuario usuario = new Usuario();
        usuario.setEmail(this.email);
        usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));

        return usuario;
    }

}