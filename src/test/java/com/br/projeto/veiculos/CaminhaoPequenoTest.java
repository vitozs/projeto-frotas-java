package com.br.projeto.veiculos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CaminhaoPequenoTest {
    @DisplayName("Teste Caminhão Pequeno")
    @Test
    public void testCaminhaoPequeno() {
        CaminhaoPequeno caminhaoPequeno = new CaminhaoPequeno();

        Assertions.assertEquals(1000, caminhaoPequeno.getCapacidade());
        Assertions.assertEquals(5.83, caminhaoPequeno.getCustoPorKm());
    }
}
