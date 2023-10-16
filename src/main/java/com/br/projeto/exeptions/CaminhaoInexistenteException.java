package com.br.projeto.exeptions;

public class CaminhaoInexistenteException extends RuntimeException{

    public CaminhaoInexistenteException(String mensagem) {super(mensagem);}
}
