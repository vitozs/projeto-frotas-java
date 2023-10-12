package com.br.projeto.veiculos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class CaminhoesHashMapTest {

    @DisplayName("Teste do método hashMapVeiculos")
    @Test
    public void testHashMapVeiculos() {
        HashMap<String, Veiculo> caminhoes = CaminhoesHashMap.hashMapVeiculos();

        // Testa as chaves do HashMap
        Assertions.assertTrue(caminhoes.containsKey("PEQUENO"));
        Assertions.assertTrue(caminhoes.containsKey("MEDIO"));
        Assertions.assertTrue(caminhoes.containsKey("GRANDE"));

        // Testa os valores do HashMap que seriam os caminhoes
        Assertions.assertNotNull(caminhoes.get("PEQUENO"));
        Assertions.assertNotNull(caminhoes.get("MEDIO"));
        Assertions.assertNotNull(caminhoes.get("GRANDE"));
    }
}
