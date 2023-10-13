package com.br.projeto.calculadora;

import com.br.projeto.exeptions.CidadeInexistenteException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    Calculadora calculadora = new Calculadora();

    @DisplayName("Testa se retorna a distancia certa entre as cidades")
    @Test
    void getDistanciaEntreCidadesTest(){
        calculadora.setCidade("natal");
        calculadora.setDestino("belem");
        int distancia = calculadora.getDistanciaEntreCidades().intValue();

        Assert.assertEquals(2108, distancia);

    }

    @DisplayName("Testa se retorna o valor correto de custo p/km")
    @Test
    void valorTotalTest(){
        calculadora.setCidade("natal");
        calculadora.setDestino("belem");
        calculadora.setTamanhoCaminhao("grande");

        Assert.assertEquals(61574.68, calculadora.valorTotal(), 0);

    }

    @Test
    void returnException () {
        Assertions.assertThrows(CidadeInexistenteException.class, () ->{
            calculadora.setCidade("Toronto");
            calculadora.setDestino("Miami");
            calculadora.valorTotal();
        });
    }


}
