package br.com.ca.vou_de_busao.exceptions;

public class ExcursaoNotFoundException extends RuntimeException {
    public ExcursaoNotFoundException(Long id){
        super(String.format("Excursão com id = %s não encontrada.\n", id));
    }
}
