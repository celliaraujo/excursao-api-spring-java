package br.com.ca.vou_de_busao.repository;

import br.com.ca.vou_de_busao.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findAll();
}
