package com.br.projeto.calculadora;

import com.br.projeto.veiculos.Veiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MelhorFrotaCaminhoes {
    public static List<Veiculo> encontraMelhorFrota(HashMap<String, Veiculo> caminhoesDisponiveis, double cargaEntrega, double distanciaTotal) {
        if (cargaEntrega <= 0) {

            return new ArrayList<>();
        }

        List<Veiculo> melhorCombinacao = new ArrayList<>();
        double menorCusto = Double.MAX_VALUE;

        for (Veiculo caminhao : caminhoesDisponiveis.values()) {
            if (cargaEntrega <= caminhao.getCapacidade()) {
                List<Veiculo> combinacaoAtual = new ArrayList<>();
                combinacaoAtual.add(caminhao);

                double custoAtual = calcularCusto(distanciaTotal, combinacaoAtual);

                if (custoAtual < menorCusto) {
                    menorCusto = custoAtual;
                    melhorCombinacao = combinacaoAtual;
                }
            } else {

                List<Veiculo> combinacaoAtual = encontraMelhorFrota(caminhoesDisponiveis, cargaEntrega - caminhao.getCapacidade(), distanciaTotal);
                combinacaoAtual.add(caminhao);

                double custoAtual = calcularCusto(distanciaTotal, combinacaoAtual);

                if (custoAtual < menorCusto) {
                    menorCusto = custoAtual;
                    melhorCombinacao = combinacaoAtual;
                }
            }
        }

        return melhorCombinacao;
    }

    public static double calcularCusto(double distanciaTotal, List<Veiculo> caminhoes) {
        double custoTotal = 0;
        for (Veiculo caminhao : caminhoes) {
            custoTotal += distanciaTotal * caminhao.getCustoPorKm();
        }
        return custoTotal;
    }


}

