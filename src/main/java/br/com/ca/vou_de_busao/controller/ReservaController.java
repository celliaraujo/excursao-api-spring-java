package br.com.ca.vou_de_busao.controller;

import br.com.ca.vou_de_busao.dto.ReservaDTO;
import br.com.ca.vou_de_busao.exceptions.ExcursaoNotFoundException;
import br.com.ca.vou_de_busao.exceptions.PassageiroNotFoundException;
import br.com.ca.vou_de_busao.exceptions.ReservaNotFoundException;
import br.com.ca.vou_de_busao.model.Excursao;
import br.com.ca.vou_de_busao.model.Passageiro;
import br.com.ca.vou_de_busao.model.Reserva;
import br.com.ca.vou_de_busao.model.StatusReserva;
import br.com.ca.vou_de_busao.repository.ExcursaoRepository;
import br.com.ca.vou_de_busao.repository.PassageiroRepository;
import br.com.ca.vou_de_busao.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private PassageiroRepository passageiroRepository;
    @Autowired
    private ExcursaoRepository excursaoRepository;


    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ReservaDTO dto) {
        Passageiro passageiro = passageiroRepository.findById(dto.getPassageiroId())
                .orElseThrow(() -> new PassageiroNotFoundException(dto.getPassageiroId()));
        Excursao excursao = excursaoRepository.findById(dto.getExcursaoId())
                .orElseThrow(() -> new ExcursaoNotFoundException(dto.getExcursaoId()));

        Reserva reserva = new Reserva();
        reserva.setStatus(dto.getStatus());
        reserva.setPassageiro(passageiro);
        reserva.setExcursao(excursao);

        Reserva salva = reservaRepository.save(reserva);
        return ResponseEntity.status(201).body(salva);
    }


    @GetMapping
    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }


    @GetMapping("/buscar")
    public List<Reserva> buscarPorStatus(@RequestParam StatusReserva status) {
        return reservaRepository.findByStatus(status);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestParam StatusReserva status) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException(id));


        Reserva reservaAlt = new Reserva();
        reservaAlt.setStatus(status);

        Reserva salva = reservaRepository.save(reservaAlt);
        return ResponseEntity.status(201).body(salva);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
