package com.br.projeto.pdf;

public interface Relatorio {
    public void gerarCabecalho();
    public void gerarConteudo();
    public void gerarRodape();
    public void imprimir();
}
