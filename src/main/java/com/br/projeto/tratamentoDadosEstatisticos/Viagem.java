package com.br.projeto.tratamentoDadosEstatisticos;

import com.br.projeto.produtos.Produtos;
import com.br.projeto.veiculos.Veiculo;

import java.util.List;

public class Viagem {

    private double DistanciaTotal;
    private double PesoTotal;
    private double CustoTotal;
    private double PrecoUnitario;
    private List<Veiculo> CombinacaoVeiculos;
    private List<Produtos> listaProdutos;
    private String cidadeOrigem;
    private String cidadeDestino;


    private int qtdTotalProdutos;
    public Viagem(double distanciaTotal, double pesoTotal, double custoTotal, double precoUnitario, List<Veiculo> combinacaoVeiculos, int qtdTotal, List<Produtos> listaProdutos, String cidadeOrigem, String cidadeDestino) {
        DistanciaTotal = distanciaTotal;
        PesoTotal = pesoTotal;
        CustoTotal = custoTotal;
        PrecoUnitario = precoUnitario;
        CombinacaoVeiculos = combinacaoVeiculos;
        qtdTotalProdutos = qtdTotal;
        this.listaProdutos = listaProdutos;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
    }

    public double getDistanciaTotal() {
        return DistanciaTotal;
    }

    public double getPesoTotal() {
        return PesoTotal;
    }

    public double getCustoTotal() {
        return CustoTotal;
    }

    public double getPrecoUnitario() {
        return PrecoUnitario;
    }

    public List<Veiculo> getCombinacaoVeiculos() {
        return CombinacaoVeiculos;
    }

    public int getQtdTotalProdutos() {
        return qtdTotalProdutos;
    }

    public List<Produtos> getListaProdutos() {
        return listaProdutos;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }
}
