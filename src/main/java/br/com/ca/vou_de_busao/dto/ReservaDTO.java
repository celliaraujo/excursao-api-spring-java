package br.com.ca.vou_de_busao.dto;

import br.com.ca.vou_de_busao.model.StatusReserva;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaDTO {
    private Long passageiroId;
    private Long excursaoId;
    private StatusReserva status;
}
