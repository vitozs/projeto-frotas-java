package com.br.projeto.veiculos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CaminhaoGrandeTest {

    @DisplayName("Teste Caminhão Grande")
    @Test
    public void testCaminhaoGrande() {
        CaminhaoGrande caminhaoGrande = new CaminhaoGrande();

        Assertions.assertEquals(10, caminhaoGrande.getCapacidade());
        Assertions.assertEquals(29.21, caminhaoGrande.getCustoPorKm());
    }
}
