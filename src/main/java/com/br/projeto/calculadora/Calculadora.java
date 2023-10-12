package com.br.projeto.calculadora;

import com.br.projeto.util.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Objects;

public class Calculadora {



    public Long getDistanciaEntreCidades(String cidade, String destino){    //Retorna a distancia entre as cidades passadas
        //Acessa o metodo que retorna o json tratado
        JSONArray distancias = JsonReader.lerArquivoJson();
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

}
