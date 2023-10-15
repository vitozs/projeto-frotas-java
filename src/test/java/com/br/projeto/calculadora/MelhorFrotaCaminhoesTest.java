package com.br.projeto.calculadora;

import com.br.projeto.veiculos.CaminhoesHashMap;
import com.br.projeto.veiculos.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

public class MelhorFrotaCaminhoesTest {
   LinkedHashMap<String, Veiculo> caminhoesDisponiveis =  CaminhoesHashMap.hashMapVeiculos();

    @DisplayName("Testa Caminhao Pequeno")
    @Test
    public void testaPequeno(){

        double cargaTotalDaEntrega = 1000;
        double distanciaTotal = 2000;

        List<Veiculo> listMelhorFrota = MelhorFrotaCaminhoes.encontraMelhorFrota(caminhoesDisponiveis, cargaTotalDaEntrega, distanciaTotal);

        Assertions.assertEquals("PEQUENO", listMelhorFrota.get(0).getTipo());

    }


    @DisplayName("Testa Caminhao MEDIO")
    @Test
    public void testaMedio(){

        double cargaTotalDaEntrega = 4000;
        double distanciaTotal = 2000;

        List<Veiculo> listMelhorFrota = MelhorFrotaCaminhoes.encontraMelhorFrota(caminhoesDisponiveis, cargaTotalDaEntrega, distanciaTotal);

        Assertions.assertEquals("MEDIO", listMelhorFrota.get(0).getTipo());

    }



    @DisplayName("Testa Frota muito Grande")
    @Test
    public void testaFrotaMuitoGrande(){

        double cargaTotalDaEntrega = 26000;
        double distanciaTotal = 1109;

        List<Veiculo> listMelhorFrota = MelhorFrotaCaminhoes.encontraMelhorFrota(caminhoesDisponiveis, cargaTotalDaEntrega, distanciaTotal);

        Assertions.assertEquals("MEDIO", listMelhorFrota.get(0).getTipo());
        Assertions.assertEquals("MEDIO", listMelhorFrota.get(1).getTipo());
        Assertions.assertEquals("GRANDE", listMelhorFrota.get(2).getTipo());
        Assertions.assertEquals("MEDIO", listMelhorFrota.get(3).getTipo());
        Assertions.assertEquals("MEDIO", listMelhorFrota.get(4).getTipo());

    }
    @DisplayName("Testa Caminhao GRANDE")
    @Test
    public void testaGrande(){

        double cargaTotalDaEntrega = 10000;
        double distanciaTotal = 2000;

        List<Veiculo> listMelhorFrota = MelhorFrotaCaminhoes.encontraMelhorFrota(caminhoesDisponiveis, cargaTotalDaEntrega, distanciaTotal);

        Assertions.assertEquals("GRANDE", listMelhorFrota.get(0).getTipo());

    }


    @DisplayName("Testa Multipla Combinacao")
    @Test
    public void testaMelhorFrotaMultipla(){

        double cargaTotalDaEntrega = 5000;
        double distanciaTotal = 8500;

        List<Veiculo> listMelhorFrota = MelhorFrotaCaminhoes.encontraMelhorFrota(caminhoesDisponiveis, cargaTotalDaEntrega, distanciaTotal);

        Assertions.assertEquals("PEQUENO", listMelhorFrota.get(0).getTipo());
        Assertions.assertEquals("MEDIO", listMelhorFrota.get(1).getTipo());

    }
}
