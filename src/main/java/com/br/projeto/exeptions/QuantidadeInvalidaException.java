package com.br.projeto.exeptions;

public class QuantidadeInvalidaException extends RuntimeException{

    public QuantidadeInvalidaException(String mensagem){
        super(mensagem);
    }
}
