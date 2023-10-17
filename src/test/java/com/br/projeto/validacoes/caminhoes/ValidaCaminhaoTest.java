package com.br.projeto.validacoes.caminhoes;

import com.br.projeto.exeptions.CaminhaoInexistenteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidaCaminhaoTest {

    @DisplayName("Teste Valida Caminhao")
    @Test
    public void validaCaminhao(){
        ValidaCaminhao valida = new ValidaCaminhao();

        Assertions.assertTrue(valida.validadorTipoCaminhao("PEQUENO"));

        Assertions.assertThrows(CaminhaoInexistenteException.class, () -> valida.validadorTipoCaminhao("JAGUARA"));
    }
}
