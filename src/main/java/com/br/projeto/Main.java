package com.br.projeto;
import com.br.projeto.exeptions.OpcaoInvalidaException;
import com.br.projeto.produtos.Produtos;
import com.br.projeto.util.JsonReader;
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

    }
    private static void metodo2(){
        //altere nome do metodo para chamada da class com o metodo
        Produtos produtos = new Produtos();
        String opt = "";
        String cidadeOrigem, cidadeDestino;
        List<Produtos> listProdutos = null;

        while(!Objects.equals(opt, "n")){
            produtos.adicionarProduto();
            System.out.println("Deseja continuar? (s/n) ");
            opt = scan.next();
        }

        listProdutos = produtos.getProdutosList();

        System.out.println("Digite a cidade de origem: ");
        cidadeOrigem = scan.nextLine();
        System.out.println("Digite a cidade de destino: ");
        cidadeDestino = scan.nextLine();

    }
    private static void metodo3(){
        //altere nome do metodo para chamada da class com o metodo
    }


}
