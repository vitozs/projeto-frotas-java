package com.br.projeto.calculadora;

import com.br.projeto.veiculos.Veiculo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MelhorFrotaCaminhoes {

    public static double menorCustoTotal;
    public static List<Veiculo> encontraMelhorFrota(LinkedHashMap<String, Veiculo> caminhoesDisponiveis, double cargaEntrega, double distanciaTotal) {

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

                // Se o custo atual for maior do que o menorCusto pode parar a recursao pois nao precisa verificar outras possibilidades nessa direcao
                if (custoAtual >= menorCusto) return melhorCombinacao;

                menorCusto = custoAtual;
                melhorCombinacao = combinacaoAtual;
            }
        }

        menorCustoTotal = menorCusto;
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

