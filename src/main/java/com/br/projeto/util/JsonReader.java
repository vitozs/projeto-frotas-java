package com.br.projeto.util;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Classe que retorna os valores das distancias para ser feito os Calculos
public class JsonReader {


    public static JSONArray lerArquivoJson(){
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
