package com.br.projeto.calculadora;

import com.br.projeto.veiculos.Veiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MelhorFrotaCaminhoes {
    public static List<Veiculo> encontraMelhorFrota(HashMap<String, Veiculo> caminhoesDisponiveis, double cargaEntrega, double distanciaTotal) {

        List<Veiculo> melhorCombinacao = new ArrayList<>();
        double menorCusto = Double.MAX_VALUE;

        for (Veiculo caminhaoAtual : caminhoesDisponiveis.values()) {
            if (cargaEntrega <= caminhaoAtual.getCapacidade()) {
                List<Veiculo> combinacaoAtual = new ArrayList<>();
                combinacaoAtual.add(caminhaoAtual);

                double custoAtual = calcularCustoCombinacao(distanciaTotal, combinacaoAtual);

                if (custoAtual < menorCusto) {
                    menorCusto = custoAtual;
                    melhorCombinacao = combinacaoAtual;
                }
            } else {

                List<Veiculo> combinacaoAtual = encontraMelhorFrota(caminhoesDisponiveis, cargaEntrega - caminhaoAtual.getCapacidade(), distanciaTotal);
                combinacaoAtual.add(caminhaoAtual);

                double custoAtual = calcularCustoCombinacao(distanciaTotal, combinacaoAtual);

                if (custoAtual < menorCusto) {
                    menorCusto = custoAtual;
                    melhorCombinacao = combinacaoAtual;
                }
            }
        }

        return melhorCombinacao;
    }

    private static double calcularCustoCombinacao(double distanciaTotal, List<Veiculo> caminhoes) {
        double custoTotal = 0;
        for (Veiculo caminhao : caminhoes) {
            custoTotal += distanciaTotal * caminhao.getCustoPorKm();
        }
        return custoTotal;
    }


}

