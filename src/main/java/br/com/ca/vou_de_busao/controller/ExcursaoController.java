package br.com.ca.vou_de_busao.controller;

import br.com.ca.vou_de_busao.model.Excursao;
import br.com.ca.vou_de_busao.repository.ExcursaoRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/excursoes")
public class ExcursaoController {

    @Autowired
    private ExcursaoRepository excursaoRepository;

    @PostMapping
    public ResponseEntity<Excursao> criar(@RequestBody Excursao excursao) {
        Excursao salva = excursaoRepository.save(excursao);
        return ResponseEntity.status(201).body(salva);
    }

    @GetMapping
    public List<Excursao> listar() {
        return excursaoRepository.findAll();
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorDestino(@RequestParam String destino) {
        List<Excursao> resultados = excursaoRepository.findByDestinoContainingIgnoreCase(destino);
        if (resultados.isEmpty()) {
            String mensagem = "Nenhum destino encontrado com o nome: " + destino;
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(mensagem);
        }

        return ResponseEntity.ok(resultados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Excursao> atualizar(@PathVariable Long id, @RequestBody Excursao novaExcursao) {
        return excursaoRepository.findById(id)
                .map(excursao -> {
                    excursao.setDestino(novaExcursao.getDestino());
                    excursao.setData(novaExcursao.getData());
                    excursao.setPreco(novaExcursao.getPreco());
                    return ResponseEntity.ok(excursaoRepository.save(excursao));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (excursaoRepository.existsById(id)) {
            excursaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
