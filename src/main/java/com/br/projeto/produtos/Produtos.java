package com.br.projeto.produtos;

import com.br.projeto.util.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Produtos {


    public void getListaDeProdutos() {
        /**/
        JSONArray produtos = JsonReader.lerArquivoJson("src/main/resources/json/relacao_cidades.json");
            System.out.println("= Lista de Produtos =");
        for (Object produtosJson : produtos) {
            JSONObject produto = (JSONObject) produtosJson;
            System.out.print(produto.get("nome"));
            System.out.print(" ");
            System.out.println(produto.get("peso"));
        }
    }


}
