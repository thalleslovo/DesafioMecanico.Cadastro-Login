package Desafio.Mecanico.Api.Controller;

import Desafio.Mecanico.Api.pastas.Cadastro.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/cadastro")
public class CadastroUsuario {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public void cadastrousario(@RequestBody @Valid DadosCadastro dados){
        repository.save(new Usuario(dados));

    }

    @GetMapping
    public Page<DadosListagemUsuario> listar(Pageable paginacao ){
        return repository.findAll(paginacao).map(DadosListagemUsuario::new);


    }

    @PutMapping
    @Transactional
    public void autualizar(@RequestBody @Valid DadosAtualizacao dados){
        var Usuario = repository.getReferenceById(dados.id());
        Usuario.atualizarInformacoes(dados);
    }


}
