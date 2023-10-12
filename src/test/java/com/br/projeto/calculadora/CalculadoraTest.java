package com.br.projeto.calculadora;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    Calculadora calculadora = new Calculadora();

    @DisplayName("Testa se retorna a distancia certa entre as cidades")
    @Test
    void getDistanciaTest(){
        int distancia = calculadora.getDistanciaEntreCidades("natal", "belem").intValue();

        Assert.assertEquals(2108, distancia);

    }

}
