package com.ib.asistencia.controller;

import com.ib.asistencia.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ApiUsuarios {

    /*@Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public List<Usuario> usuarios(Usuario usuario){
        var usuarios = usuarioService.listarUsuarios();
        return usuarios;
    }

    @PostMapping("")
    public Usuario guardar(@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }

    @PutMapping("{idUsuario}")
    Usuario upDatePerson(@PathVariable String idUsuario, @RequestBody @NotNull Usuario usuario){
        Usuario usuarioFrontDb = usuarioService.encontrarUsuario(idUsuario);
        return usuarioService.guardar(usuarioFrontDb);
    }

    @PostMapping("/eliminar")
    public void eliminar(@RequestBody Usuario usuario){
        usuarioService.eliminar(usuario);
    }*/

/*    @PostMapping("/usuarios")
    public Usuario login(@RequestParam("login") String username, @RequestParam("password") String pwd) {

        String token = getJWTToken(username);
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setToken(token);
        return usuario;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }*/

}
