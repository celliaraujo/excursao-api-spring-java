package br.com.ca.vou_de_busao.repository;

import br.com.ca.vou_de_busao.model.Excursao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ExcursaoRepository extends JpaRepository<Excursao, Long> {
    List<Excursao> findByDestinoContainingIgnoreCase(String destino);
}
