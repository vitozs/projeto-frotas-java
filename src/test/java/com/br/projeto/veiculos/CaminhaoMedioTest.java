package com.br.projeto.veiculos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CaminhaoMedioTest {

    @DisplayName("Teste Caminhão Medio")
    @Test
    public void testCaminhaoMedio() {
        CaminhaoMedio caminhaoMedio = new CaminhaoMedio();

        Assertions.assertEquals(4000, caminhaoMedio.getCapacidade());
        Assertions.assertEquals(13.42, caminhaoMedio.getCustoPorKm());
    }
}


