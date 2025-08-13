package br.com.ca.vou_de_busao.exceptions;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(Long id){

        super(String.format("Reserva com id = %s não encontrada.\n", id));
    }
}
