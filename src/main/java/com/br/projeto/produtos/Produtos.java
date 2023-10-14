package com.br.projeto.produtos;

import com.br.projeto.util.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.*;

public class Produtos {

    private String nome;
    private double peso;
    private int quantidade;


    public Produtos() {
    }

    public Produtos(String nome, double peso, int quantidade) {
        this.nome = nome;
        this.peso = peso;
        this.quantidade = quantidade;
    }

    List<Produtos> produtosList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void getListaDeProdutos() {
        /**/
        JSONArray produtos = JsonReader.lerArquivoJson("src/main/resources/json/produtos.json");
        System.out.println("= Lista de Produtos =");
        for (Object produtosJson : produtos) {
            JSONObject produto = (JSONObject) produtosJson;
            System.out.print("id: ");
            System.out.print(produto.get("id"));
            System.out.print(", nome: ");
            System.out.print(produto.get("nome"));
            System.out.print(", peso: ");
            System.out.println(produto.get("peso"));
        }
    }


    public void adicionarProduto() {
        JSONArray produtos = JsonReader.lerArquivoJson("src/main/resources/json/produtos.json");
        boolean produtoEncontrado = false;

        System.out.print("Digite o id do produto: ");
        int idSelecionado = scanner.nextInt();
        System.out.print("Digite a quantidade de produtos: ");
        int quantidadeProduto = scanner.nextInt();

        for (Object produtosJson : produtos) {
            JSONObject produto = (JSONObject) produtosJson;
            int idProduto = Integer.parseInt(produto.get("id").toString());

            if (idSelecionado == idProduto ) {
                String nomeProduto = produto.get("nome").toString();
                double pesoProduto = Double.parseDouble(produto.get("peso").toString());
                Produtos novoProduto = new Produtos(nomeProduto, pesoProduto, quantidadeProduto);
                produtosList.add(novoProduto);
                produtoEncontrado = true;
            }
        }

        if (produtoEncontrado) {
            System.out.println("Produto adicionado com sucesso.");
            System.out.print(produtos.get(idSelecionado -1));
            System.out.println(" Quantidade: " + quantidadeProduto);
        } else {
            System.out.println("Produto não encontrado com o ID especificado.");
        }
    }
}
