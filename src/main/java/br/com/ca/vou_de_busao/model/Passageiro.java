package br.com.ca.vou_de_busao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Passageiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String cpf;
    private String nome;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "excursao_id")
    @JsonIgnore
    private Excursao excursao;

    @OneToMany(mappedBy = "passageiro", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reserva> reservas = new ArrayList<>();
}
