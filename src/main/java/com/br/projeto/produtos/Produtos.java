package com.br.projeto.produtos;

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
        JSONArray produtos = lerArquivoJsonProdutos();
            System.out.println("= Lista de Produtos =");
        for (Object produtosJson : produtos) {
            JSONObject produto = (JSONObject) produtosJson;
            System.out.print(produto.get("nome"));
            System.out.print(" ");
            System.out.println(produto.get("peso"));
        }
    }


    public JSONArray lerArquivoJsonProdutos() {
        try {
            /*Lê o arquivo Json*/
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/main/resources/json/produtos.json");
            /*Transforma o Json em Objeto*/
            Object objectJson = parser.parse(reader);

            if (objectJson instanceof JSONArray) {
                return (JSONArray) objectJson;
            } else {
                throw new RuntimeException("O arquivo JSON não contém um objeto JSON.");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
