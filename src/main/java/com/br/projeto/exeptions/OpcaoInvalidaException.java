package com.br.projeto.exeptions;

public class OpcaoInvalidaException extends  RuntimeException{
    public OpcaoInvalidaException(String mensagem){
        super(mensagem);
    }
}
