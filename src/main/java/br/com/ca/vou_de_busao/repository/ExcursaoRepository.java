package br.com.ca.vou_de_busao.repository;

import br.com.ca.vou_de_busao.model.Excursao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursaoRepository extends JpaRepository<Excursao, Long> {
}
