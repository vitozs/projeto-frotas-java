package com.br.projeto.veiculos;

public abstract class Veiculo {
    private final int capacidade;  //KG

    private final double custoPorKm;

    private final String tipo;

    public Veiculo(int capacidade, double custoPorKm, String tipoCaminhao) {
        this.capacidade = capacidade;
        this.custoPorKm = custoPorKm;
        this.tipo = tipoCaminhao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getCustoPorKm() {
        return custoPorKm;
    }

    public String getTipo() {
        return tipo;
    }
}
