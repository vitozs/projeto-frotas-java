package com.br.projeto.calculadora;

import com.br.projeto.exeptions.CidadeInexistenteException;
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
        Long distanciaTotal = 0L;
        //Se a cidade e o destino forem validos, pega a distancia
        if(validadorCidadeDestino(this.cidade) && validadorCidadeDestino(this.destino)){
            //Acessa o metodo que retorna o json tratado
            JSONArray distancias = JsonReader.lerArquivoJson("src/main/resources/json/relacao_cidades.json");

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


        }
        return distanciaTotal;
    }

    public double valorTotal(){
        int distancia = getDistanciaEntreCidades().intValue(); //Faz o cast de Long para int

        double custoKm = caminhoes.get(this.tamanhoCaminhao).getCustoPorKm(); //Pega o valor p/Km do tamanho do caminhao dentro do hashmap

        //retorna o valor total
        return distancia * custoKm;
    }

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
                    throw new CidadeInexistenteException("Cidade ou Destino Invalidos!"); //se for falso, retorna uma exception
                }
            }
        }catch (NullPointerException e){
            System.err.println("Campos invalidos! Digite os campos corretamente!");
        }


        return flag;
    }

    public void setCidade(String cidade) {
        if(validadorCidadeDestino(cidade.trim())){
            this.cidade = cidade.toUpperCase().trim();
        }

    }

    public void setDestino(String destino) {
        if(validadorCidadeDestino(destino.trim())){
            this.destino = destino.toUpperCase().trim();
        }

    }

    public void setTamanhoCaminhao(String tamanhoCaminhao) {
        this.tamanhoCaminhao = tamanhoCaminhao.toUpperCase().trim();
    }
}
