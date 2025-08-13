package br.com.ca.vou_de_busao.controller;

import br.com.ca.vou_de_busao.model.Passageiro;
import br.com.ca.vou_de_busao.repository.PassageiroRepository;
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
    private PassageiroRepository passageiroRepository;

    @GetMapping
    public List<Passageiro> listarTodos(){
        return passageiroRepository.findAll();
    }

    @GetMapping("/nome/{nome}")
    public List<Passageiro> listarPorNome(@PathVariable String nome){

        return passageiroRepository.findByNome(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passageiro> buscarPorId(@PathVariable Long id){
        return passageiroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Passageiro passageiro){
        if (passageiroRepository.existsByCpf(passageiro.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um passageiro com esse CPF.");
        }
        Passageiro salvo = passageiroRepository.save(passageiro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passageiro> atualizar(@PathVariable Long id, @RequestBody Passageiro dados){
        Optional<Passageiro> passageiro = passageiroRepository.findById(id);

        if(passageiro.isPresent()) {
            Passageiro p = passageiro.get();
            p.setNome(dados.getNome());
            p.setCpf(dados.getCpf());
            p.setTelefone(dados.getTelefone());
            passageiroRepository.save(p);

            return ResponseEntity.ok(p);

        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(passageiroRepository.existsById(id)){
            passageiroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
