package Desafio.Mecanico.Api.pastas.Cadastro;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacao(

        @NotNull
        Long id ,

        String senha) {
}
