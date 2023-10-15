package com.br.projeto.exeptions;

public class CidadeInexistenteException extends RuntimeException {
    public CidadeInexistenteException (String mensagem){
        super(mensagem);
    } 

}
