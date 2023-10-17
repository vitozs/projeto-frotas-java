package com.br.projeto.validacoes.cidades;

import com.br.projeto.exeptions.CidadeInexistenteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidaCidadeTest {

    @DisplayName("Teste Valida Caminhao")
    @Test
    public void validaCidade(){
        ValidaCidade valida = new ValidaCidade();

        Assertions.assertTrue(valida.validadorCidadeDestino("PORTO ALEGRE"));

        Assertions.assertThrows(CidadeInexistenteException.class, () -> valida.validadorCidadeDestino("JAGUARA"));
    }
}
