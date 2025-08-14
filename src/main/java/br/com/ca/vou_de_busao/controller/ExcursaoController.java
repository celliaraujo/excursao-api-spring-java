package br.com.ca.vou_de_busao.controller;

import br.com.ca.vou_de_busao.exceptions.ExcursaoNotFoundException;
import br.com.ca.vou_de_busao.model.Excursao;
import br.com.ca.vou_de_busao.service.ExcursaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/excursoes")
public class ExcursaoController {

    @Autowired
    private ExcursaoService excursaoService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Excursao excursao) {
        try{
            Excursao salva = excursaoService.criar(excursao);
            return ResponseEntity.status(HttpStatus.CREATED).body(salva);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public List<Excursao> listar() {
        return excursaoService.listar();
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorDestino(@RequestParam String destino) {
        try{
            List<Excursao> resultados = excursaoService.buscarPorDestino(destino);
            return ResponseEntity.ok(resultados);
        }catch(ExcursaoNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Excursao novaExcursao) {
        try{
            Excursao atualizada = excursaoService.atualizar(id, novaExcursao);
            return ResponseEntity.ok(atualizada);
        }catch(IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try{
            excursaoService.deletar(id);
            return ResponseEntity.noContent().build();
        }catch(ExcursaoNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
