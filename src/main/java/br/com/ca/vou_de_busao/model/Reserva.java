package br.com.ca.vou_de_busao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusReserva status;
    @ManyToOne
    @JoinColumn(name = "passageiro_id")
    private Passageiro passageiro;

    @ManyToOne
    @JoinColumn(name = "excursao_id")
    private Excursao excursao;
}
