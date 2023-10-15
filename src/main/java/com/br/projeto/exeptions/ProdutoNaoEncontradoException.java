package com.br.projeto.exeptions;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
