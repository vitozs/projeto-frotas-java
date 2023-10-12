package com.br.projeto.veiculos;

public abstract class Veiculo {
    private final int capacidade;  //KG

    private final double custoPorKm;

    public Veiculo(int capacidade, double custoPorKm) {
        this.capacidade = capacidade;
        this.custoPorKm = custoPorKm;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getCustoPorKm() {
        return custoPorKm;
    }
}
