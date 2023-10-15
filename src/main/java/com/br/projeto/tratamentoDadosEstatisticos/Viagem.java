package com.br.projeto.tratamentoDadosEstatisticos;

import com.br.projeto.veiculos.Veiculo;

import java.util.List;

public class Viagem {

    private double DistanciaTotal;
    private double PesoTotal;
    private double CustoTotal;
    private double PrecoUnitario;
    private List<Veiculo> CombinacaoVeiculos;

    private int qtdTotalProdutos;
    public Viagem(double distanciaTotal, double pesoTotal, double custoTotal, double precoUnitario, List<Veiculo> combinacaoVeiculos, int qtdTotal) {
        DistanciaTotal = distanciaTotal;
        PesoTotal = pesoTotal;
        CustoTotal = custoTotal;
        PrecoUnitario = precoUnitario;
        CombinacaoVeiculos = combinacaoVeiculos;
        qtdTotalProdutos = qtdTotal;
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
}
