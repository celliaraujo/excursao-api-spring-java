package br.com.ca.vou_de_busao.repository;

import br.com.ca.vou_de_busao.model.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
    boolean existsByCpf(String cpf);

}
