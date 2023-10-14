package com.br.projeto.tratamentoDadosEstatisticos;

import com.br.projeto.veiculos.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class TratarDados {

    private static List<Viagem> viagens = new ArrayList<Viagem>();
    public static void adicionarViagem(Viagem viagem){
        viagens.add(viagem);
    }
    public static double custoTotalViagens(){
        double total = 0;
        for (Viagem viagem : viagens){
            total += viagem.getCustoTotal();
        }
        return total; //retorna o valor total da soma de todas as viagens
    }

    public static void custoPorTrecho(){
        for (Viagem viagem : viagens){
            System.out.println("======================");
            System.out.println("       TRECHO " + (viagens.indexOf(viagem) + 1));
            System.out.println("======================");
            System.out.printf("Custo total: %.2f \n", viagem.getCustoTotal());
        }
    }

    public static int numeroTotalVeiculosTransportados(){
        int totalVeiculos = 0;
        for(Viagem viagem : viagens){
            List<Veiculo> veiculos = viagem.getCombinacaoVeiculos();
            totalVeiculos += veiculos.size();
        }
        return totalVeiculos;
    }

    public static int numeroTotalProdutos(){
        int totalProdutos = 0;
        for(Viagem viagem : viagens){
            totalProdutos += viagem.getQtdTotalProdutos();
        }
        return totalProdutos;
    }

    public static void custoTotalPorModalidade(){
        for (Viagem viagem : viagens){
            List<Veiculo> veiculos = viagem.getCombinacaoVeiculos();
            for (Veiculo veiculo : veiculos){
                System.out.printf("Total veiculo " + veiculo.getTipo() + ": %.2f \n" , veiculo.getCustoPorKm() * viagem.getDistanciaTotal());
            }
        }
    }

    public static double custoMedioPorKm(){
        double valorTotal = 0;
        double media = 0;
        for (Viagem viagem : viagens){
            valorTotal += viagem.getCustoTotal();
        }
        media = valorTotal / viagens.size();
        return media;
    }
}
