package com.br.projeto.calculadora;

import com.br.projeto.util.JsonReader;
import com.br.projeto.veiculos.CaminhoesHashMap;
import com.br.projeto.veiculos.Veiculo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class Calculadora {

    private String cidade;
    private String destino;

    private String tamanhoCaminhao;
    HashMap<String, Veiculo> caminhoes = CaminhoesHashMap.hashMapVeiculos();


    //Retorna a distancia entre as cidades passadas
    public Long getDistanciaEntreCidades(){
        //Acessa o metodo que retorna o json tratado
        JSONArray distancias = JsonReader.lerArquivoJson();
        Long distanciaTotal = 0L;

        //Navega no Array de Objetos
        for(Object cidadeJson : distancias){
            //Converte em um JSONObject para poder acessar os parametros de cada cidade
            JSONObject cidadeObj = (JSONObject) cidadeJson;
            //Verifica qual cidade esta acessando
            if(Objects.equals(cidadeObj.get("CIDADE"), this.cidade.toUpperCase())){
                //Transforma o valor do campo "LOCAIS" em um JSONObject para acessar os valores
                JSONObject distanciasCidade = (JSONObject) cidadeObj.get("LOCAIS");
                //Pega o valor da cidade com o nome do campo passado pelo usuario
                distanciaTotal =  (Long) distanciasCidade.get(this.destino.toUpperCase());
            }
        }

        return distanciaTotal;
    }

    public double valorTotal(){
        int distancia = getDistanciaEntreCidades().intValue(); //Faz o cast de Long para int

        double custoKm = caminhoes.get(this.tamanhoCaminhao).getCustoPorKm(); //Pega o valor p/Km do tamanho do caminhao dentro do hashmap

        retorna o valor total
    }


    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setTamanhoCaminhao(String tamanhoCaminhao) {
        this.tamanhoCaminhao = tamanhoCaminhao.toUpperCase();
    }
}
