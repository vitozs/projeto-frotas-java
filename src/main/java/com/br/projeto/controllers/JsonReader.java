package com.br.projeto.controllers;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

//Classe que retorna os valores das distancias para ser feito os Calculos
public class JsonReader {

    public Long getDistanciaEntreCidades(String cidade, String destino){
        //Acessa o metodo que retorna o json tratado
        JSONArray distancias = lerArquivoJson();
        Long distanciaTotal = 0L;

        //Navega no Array de Objetos
        for(Object cidadeJson : distancias){
            //Converte em um JSONObject para poder acessar os parametros de cada cidade
            JSONObject cidadeObj = (JSONObject) cidadeJson;
            //Verifica qual cidade esta acessando
            if(Objects.equals(cidadeObj.get("CIDADE"), cidade.toUpperCase())){
                //Transforma o valor do campo "LOCAIS" em um JSONObject para acessar os valores
                JSONObject distanciasCidade = (JSONObject) cidadeObj.get("LOCAIS");
                //Pega o valor da cidade com o nome do campo passado pelo usuario
                distanciaTotal =  (Long) distanciasCidade.get(destino.toUpperCase());
            }
        }

        return distanciaTotal;
    }

    public JSONArray lerArquivoJson(){
        try {
            //Le o arquivo JSON
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("src/main/resources/json/relacao_cidades.json");

            //Transforma em Objeto
            Object objetoJson = parser.parse(reader);
            //Transforma o objeto em um Array de objetos
            JSONArray arrayDeDistancias = (JSONArray) objetoJson;

            return arrayDeDistancias;


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
