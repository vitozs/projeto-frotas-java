package com.br.projeto.exeptions;

public class CampoInvalidoException extends RuntimeException{
    public CampoInvalidoException(String mensagem){
        super(mensagem);
    }

}
