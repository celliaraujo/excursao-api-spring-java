package br.com.ca.vou_de_busao.exceptions;

public class PassageiroNotFoundException extends RuntimeException {
    public PassageiroNotFoundException(Long id){

        super(String.format("Passageiro com id = %s não encontrado.\n", id));
    }

    public PassageiroNotFoundException(String nome){

        super(String.format("Passageiro com nome = %s não encontrado.\n", nome));
    }
}
