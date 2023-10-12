package com.br.projeto.controllers;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonReaderTest {

    JsonReader jsonReader = new JsonReader();


    @DisplayName("Testa se pega os dados corretos")
    @Test
    void getDistanciasTest(){
        Long distancia = jsonReader.getDistanciaEntreCidades("natal", "belem");

        Assert.assertEquals(2108, distancia.intValue());
    }

}
