package com.br.projeto;
import com.br.projeto.calculadora.Calculadora;
import com.br.projeto.calculadora.MelhorFrotaCaminhoes;
import com.br.projeto.exeptions.OpcaoInvalidaException;
import com.br.projeto.produtos.Produtos;
import com.br.projeto.tratamentoDadosEstatisticos.TratarDados;
import com.br.projeto.tratamentoDadosEstatisticos.Viagem;
import com.br.projeto.util.JsonReader;
import com.br.projeto.veiculos.CaminhoesHashMap;
import com.br.projeto.veiculos.Veiculo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int opcao = 0;
        //listarCidades();

        while(opcao != 6){

            System.out.println("""
                Bem Vindo!!
                Menu                              \s
                1 -  Consultar Trechos e Modalidades
                2 -  Cadastrar transporte
                3 -  Dados estatísticos
                4 - Listar Cidades
                5 - Listar Produtos
                6  - Encerrar Programa
                Escolha sua Opção:
                """);
            try {
                opcao = scan.nextInt();
                if (opcao < 1 || opcao > 6)
                    throw new OpcaoInvalidaException("Opcao invalida. Por favor, escolha uma opcao valida (1 a 4)");
            } catch (OpcaoInvalidaException e){
                System.err.println(e.getMessage());
            }
            catch (InputMismatchException e) {
                System.err.println("Opcao invalida. Por favor, escolha um numero inteiro de (1 a 4)");
                scan.next();
            }
            switch (opcao){
                case 1 -> metodo1();
                case 2 -> metodo2();
                case 3 -> metodo3();
                case 4 -> listarCidades();
                case 5 -> listarProdutos();
                case 6 -> System.out.println("Sair"); //Apenas
            }
        }

    }
    // Função para listar as cidades
    public static void listarCidades() {
        JSONArray jsonArray = JsonReader.lerArquivoJson("src/main/resources/json/relacao_cidades.json");
        jsonArray.forEach(obj -> {
            JSONObject cidade = (JSONObject) obj;
            System.out.println(cidade.get("CIDADE"));
        });
    }

    public static void listarProdutos(){
        Produtos produto = new Produtos();
        produto.getListaDeProdutos();
    }

    private static void metodo1(){
        //altere nome do metodo para chamada da class com o metodo
        //chame sua classe aqui
        // exemplo calculadora.calculaViagem();

        String cidadeOrigem, cidadeDestino, tamanhoCaminhao;
        Calculadora calculadora = new Calculadora();

        scan.nextLine();
        System.out.println("Digite a cidade de origem: ");
        cidadeOrigem = scan.nextLine();
        calculadora.setCidade(cidadeOrigem);

        System.out.println("Digite a cidade de destino: ");
        cidadeDestino = scan.nextLine();
        calculadora.setDestino(cidadeDestino);

        System.out.println("Selecione o tamanho do caminhao: ");
        for (String tamanho : CaminhoesHashMap.hashMapVeiculos().keySet()){
            System.out.println(tamanho);
        }
        tamanhoCaminhao = scan.next();


        calculadora.setTamanhoCaminhao(tamanhoCaminhao);
        System.out.println("------------------------");
        System.out.println("Distancia total: " + calculadora.getDistanciaEntreCidades() + " Km" );
        System.out.printf("Valor total estimado: R$ %.2f \n", calculadora.valorTotal());
        System.out.println("------------------------");




    }
    private static void metodo2(){
        //altere nome do metodo para chamada da class com o metodo

        Produtos produtos = new Produtos();
        String opt = "";
        Calculadora calculadora = new Calculadora();
        String cidadeOrigem, cidadeDestino;
        double distanciaTotal, custoTotal, mediaUnitaria;
        List<Produtos> listProdutos;
        double pesoTotalProdutos = 0;

        int qtdTotal = 0;
        while(!Objects.equals(opt, "n") && !Objects.equals(opt, "N")){
            produtos.adicionarProduto();
            System.out.println("Deseja continuar? (s/n) ");
            opt = scan.next();
            if(!Objects.equals(opt, "S") && !Objects.equals(opt, "s") && !Objects.equals(opt, "N") && !Objects.equals(opt, "n")){
                throw new OpcaoInvalidaException("Opcao invalida!");
            }
        }

        listProdutos = produtos.getProdutosList();

        for (Produtos produto : listProdutos){
            pesoTotalProdutos += produto.getPeso() * produto.getQuantidade();
            qtdTotal += produto.getQuantidade();
        }
        scan.nextLine();

        System.out.println("----------------------");
        System.out.println("Digite a cidade origem: ");
        cidadeOrigem = scan.nextLine();
        calculadora.setCidade(cidadeOrigem);

        System.out.println("Digite a cidade destino: ");
        cidadeDestino = scan.nextLine();
        calculadora.setDestino(cidadeDestino);

        distanciaTotal = calculadora.getDistanciaEntreCidades();

        List<Veiculo> melhorCombinacao = MelhorFrotaCaminhoes.encontraMelhorFrota(CaminhoesHashMap.hashMapVeiculos(), pesoTotalProdutos, calculadora.getDistanciaEntreCidades());

        custoTotal = MelhorFrotaCaminhoes.menorCustoTotal;
        mediaUnitaria = (MelhorFrotaCaminhoes.menorCustoTotal / qtdTotal);

        System.out.println("----------------------");
        System.out.println("Distancia total: " + distanciaTotal + " Km");
        System.out.println("Peso total: " + pesoTotalProdutos + " Kg");
        System.out.printf("Custo total: R$ %.2f \n", custoTotal);
        System.out.printf("Preco unitario medio: R$ %.2f \n", mediaUnitaria );
        System.out.println("--------------------");
        System.out.println("Caminhoes usados: ");
        for (Veiculo veiculo : melhorCombinacao){
            System.out.println(veiculo.getTipo());
        }
        System.out.println("--------------------");

        TratarDados.adicionarViagem(new Viagem(distanciaTotal, pesoTotalProdutos, custoTotal, mediaUnitaria, melhorCombinacao , qtdTotal));


    }
    private static void metodo3(){
        //altere nome do metodo para chamada da class com o metodo
        System.out.println("==========================");
        System.out.println("||                      ||");
        System.out.println("||      RELATORIO       ||");
        System.out.println("||                      ||");
        System.out.println("==========================");
        TratarDados.custoPorTrecho();
        System.out.println("--------------------------");
        System.out.printf("\nCusto total das viagens: R$ %.2f \n" , TratarDados.custoTotalViagens());
        System.out.println("Numero total de veiculos utilizados: " + TratarDados.numeroTotalVeiculosTransportados());
        System.out.println("Numero total de produtos transportados: " + TratarDados.numeroTotalProdutos());
        TratarDados.custoTotalPorModalidade();
        System.out.printf("\nCusto medio por Km: %.2f Km\n",  TratarDados.custoMedioPorKm());
    }


}
