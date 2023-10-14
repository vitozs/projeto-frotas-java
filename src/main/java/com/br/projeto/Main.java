package com.br.projeto;
import com.br.projeto.calculadora.Calculadora;
import com.br.projeto.calculadora.MelhorFrotaCaminhoes;
import com.br.projeto.exeptions.OpcaoInvalidaException;
import com.br.projeto.produtos.Produtos;
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
        Scanner scanner = new Scanner(System.in);
        listarCidades();

        while(opcao != 4){

            System.out.println("""
                Bem Vindo!!
                Menu                              \s
                1 -  Consultar Trechos e Modalidades
                2 -  Cadastrar transporte
                3 -  Dados estatísticos
                4  - Encerrar Programa
                Escolha sua Opção:
                """);
            try {
                opcao = scanner.nextInt();
                if (opcao < 1 || opcao > 4)
                    throw new OpcaoInvalidaException("Opcao invalida. Por favor, escolha uma opcao valida (1 a 4)");
            } catch (OpcaoInvalidaException e){
                System.err.println(e.getMessage());
            }
            catch (InputMismatchException e) {
                scanner.next();
            }
            switch (opcao){
                case 1 -> metodo1();
                case 2 -> metodo2();
                case 3 -> metodo3();
                case 4 -> System.out.println("Sair"); //Apenas
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

    private static void metodo1(){
        //altere nome do metodo para chamada da class com o metodo
        //chame sua classe aqui
        // exemplo calculadora.calculaViagem();

        String cidadeOrigem, cidadeDestino, tamanhoCaminhao;
        Calculadora calculadora = new Calculadora();


        System.out.println("Digite a cidade de origem: ");
        cidadeOrigem = scan.nextLine();
        System.out.println("Digite a cidade de destino: ");
        cidadeDestino = scan.nextLine();
        System.out.println("Selecione o tamanho do caminhao: ");
        for (String tamanho : CaminhoesHashMap.hashMapVeiculos().keySet()){
            System.out.println(tamanho);
        }
        tamanhoCaminhao = scan.next();
        calculadora.setCidade(cidadeOrigem);
        calculadora.setDestino(cidadeDestino);
        calculadora.setTamanhoCaminhao(tamanhoCaminhao);
        System.out.println("Distancia total: " + calculadora.getDistanciaEntreCidades());
        System.out.println("Valor total estimado: " + calculadora.valorTotal());




    }
    private static void metodo2(){
        //altere nome do metodo para chamada da class com o metodo

        Produtos produtos = new Produtos();
        String opt = "";
        Calculadora calculadora = new Calculadora();
        String cidadeOrigem, cidadeDestino, tamanhoCaminhao;
        List<Produtos> listProdutos = null;
        double pesoTotalProdutos = 0, qtdTotal = 0;
        while(!Objects.equals(opt, "n")){
            produtos.adicionarProduto();
            System.out.println("Deseja continuar? (s/n) ");
            opt = scan.next();
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
        System.out.println("Digite a cidade destino: ");
        cidadeDestino = scan.nextLine();

        calculadora.setCidade(cidadeOrigem);
        calculadora.setDestino(cidadeDestino);

        List<Veiculo> melhorCombinacao = MelhorFrotaCaminhoes.encontraMelhorFrota(CaminhoesHashMap.hashMapVeiculos(), pesoTotalProdutos, calculadora.getDistanciaEntreCidades());

        System.out.println("----------------------");
        System.out.println("Distancia total: " + calculadora.getDistanciaEntreCidades() + " Km");
        System.out.println("Peso total: " + pesoTotalProdutos + " Kg");
        System.out.printf("Custo total: R$%.2f \n", MelhorFrotaCaminhoes.menorCustoTotal);
        System.out.printf("Preco unitario medio: R$ %.2f \n", (MelhorFrotaCaminhoes.menorCustoTotal / qtdTotal) );
        System.out.println("--------------------");
        System.out.println("Caminhoes usados: ");
        for (Veiculo veiculo : melhorCombinacao){
            System.out.println(veiculo.getTipo());
        }
        System.out.println("--------------------");



    }
    private static void metodo3(){
        //altere nome do metodo para chamada da class com o metodo
    }


}
