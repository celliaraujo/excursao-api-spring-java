package br.com.ca.vou_de_busao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Excursao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String destino;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(example = "14/08/2025", type = "string", format = "date")
    private LocalDate data;
    private BigDecimal preco;

    @JsonIgnore
    @OneToMany(mappedBy = "excursao", cascade = CascadeType.ALL)
    private List<Passageiro> passageiros = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "excursao", cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();
}
