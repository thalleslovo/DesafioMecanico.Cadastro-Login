package Desafio.Mecanico.Api.Controller;

import Desafio.Mecanico.Api.Infra.security.DadosTokenJWT;
import Desafio.Mecanico.Api.Infra.security.TokenService;
import Desafio.Mecanico.Api.pastas.login.DadosAutenticacao;
import Desafio.Mecanico.Api.pastas.login.Login;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController  {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){

        var AuthenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authentication = manager.authenticate(AuthenticationToken);

        var TokenJWT = tokenService.gerarToken((Login) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(TokenJWT));
    }
}
