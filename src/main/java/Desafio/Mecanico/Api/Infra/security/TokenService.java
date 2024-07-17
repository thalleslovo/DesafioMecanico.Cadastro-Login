package Desafio.Mecanico.Api.Infra.security;

import Desafio.Mecanico.Api.pastas.login.Login;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService  {



    public String gerarToken(Login login){

        try {
            var algorimo = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("API Desafio.Mecanico")
                    .withSubject(login.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorimo);

        }catch (JWTCreationException exception){
            throw  new RuntimeException("erro ao gerar token jwt" , exception);

        }


        }

        public String getSubject(String tokenJWT){


            try {
                var algorimo = Algorithm.HMAC256("123456");
                return JWT.require(algorimo)
                        .withIssuer("API Desafio.Mecanico")
                        .build()
                        .verify(tokenJWT)
                        .getSubject();

            } catch (JWTVerificationException exception){
                throw  new RuntimeException("Token JWT inválido ou expirado" );
            }

        }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

    }


}
