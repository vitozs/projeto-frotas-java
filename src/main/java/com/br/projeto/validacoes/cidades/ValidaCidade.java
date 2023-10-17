package com.br.projeto.validacoes.cidades;

import com.br.projeto.exeptions.CidadeInexistenteException;
import com.br.projeto.util.JsonReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ValidaCidade {
    public boolean validadorCidadeDestino(String cidade) {
        JSONArray distancias = JsonReader.lerArquivoJson("src/main/resources/json/relacao_cidades.json");
        boolean flag = false;

        try {
            while(!flag){
                for(Object cidadeJson : distancias){
                    //Converte em um JSONObject para poder acessar os parametros de cada cidade
                    JSONObject cidadeObj = (JSONObject) cidadeJson;

                    if(cidadeObj.containsValue(cidade.toUpperCase())){ //Verifica se contem o valor da cidade
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    throw new CidadeInexistenteException("Cidade ou Destino Inválidos!"); //se for falso, retorna uma exception
                }
            }
        }catch (NullPointerException e){
            System.err.println("Campos inválidos! Digite os campos corretamente!");
        }


        return flag;
    }
}
