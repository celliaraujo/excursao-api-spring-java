package br.com.ca.vou_de_busao.controller;

import br.com.ca.vou_de_busao.exceptions.PassageiroNotFoundException;
import br.com.ca.vou_de_busao.model.Passageiro;
import br.com.ca.vou_de_busao.service.PassageiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passageiros")
public class PassageiroController {
    @Autowired
    private PassageiroService passageiroService;

    @GetMapping
    public List<Passageiro> listarTodos(){
        return passageiroService.listarTodos();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> listarPorNome(@PathVariable String nome){
        try {
            List<Passageiro> resultado = passageiroService.buscarPorNome(nome);
            return ResponseEntity.ok(resultado);

        }catch (PassageiroNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Passageiro> passageiro = passageiroService.buscarPorId(id);
        return passageiro.map(ResponseEntity::ok)
                .orElseThrow(() -> new PassageiroNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Passageiro passageiro){
        Passageiro salvo = passageiroService.criar(passageiro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passageiro> atualizar(@PathVariable Long id, @RequestBody Passageiro dados){
        Optional<Passageiro> passageiro = passageiroService.buscarPorId(id);

        if(passageiro.isPresent()) {
            Passageiro p = passageiro.get();
            p.setNome(dados.getNome());
            p.setCpf(dados.getCpf());
            p.setTelefone(dados.getTelefone());
            passageiroService.atualizar(id, p);

            return ResponseEntity.ok(p);

        }else{
            throw new PassageiroNotFoundException(id);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        passageiroService.deletar(id);
        return ResponseEntity.noContent().build();

    }

}
