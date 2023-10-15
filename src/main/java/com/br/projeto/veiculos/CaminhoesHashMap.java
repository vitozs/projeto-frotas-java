package com.br.projeto.veiculos;

import java.util.HashMap;
import java.util.LinkedHashMap;

//Exemplo de HashMap com os
//[String],[Veiculo]
//[PEQUENO],[caminhaoPequeno]
//[MEDIO],[caminhaoMedio]
//[GRANDE],[caminhaoGrande]

public class CaminhoesHashMap {

    public static LinkedHashMap<String, Veiculo> hashMapVeiculos() {
        CaminhaoGrande caminhaoGrande = new CaminhaoGrande();
        CaminhaoMedio caminhaoMedio = new CaminhaoMedio();
        CaminhaoPequeno caminhaoPequeno = new CaminhaoPequeno();
        LinkedHashMap<String, Veiculo> caminhoes = new LinkedHashMap<>();

        caminhoes.put("GRANDE", caminhaoGrande);
        caminhoes.put("MEDIO", caminhaoMedio);
        caminhoes.put("PEQUENO", caminhaoPequeno);

        return caminhoes;

    }
}
