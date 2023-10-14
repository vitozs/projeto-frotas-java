package com.br.projeto.veiculos;

import java.util.HashMap;

//Exemplo de HashMap com os
//[String],[Veiculo]
//[PEQUENO],[caminhaoPequeno]
//[MEDIO],[caminhaoMedio]
//[GRANDE],[caminhaoGrande]

public class CaminhoesHashMap {

    public static HashMap<String, Veiculo> hashMapVeiculos() {
        CaminhaoPequeno caminhaoPequeno = new CaminhaoPequeno();
        CaminhaoMedio caminhaoMedio = new CaminhaoMedio();
        CaminhaoGrande caminhaoGrande = new CaminhaoGrande();
        HashMap<String, Veiculo> caminhoes = new HashMap<>();

        caminhoes.put("GRANDE", caminhaoGrande);
        caminhoes.put("MEDIO", caminhaoMedio);
        caminhoes.put("PEQUENO", caminhaoPequeno);

        return caminhoes;

    }
}
