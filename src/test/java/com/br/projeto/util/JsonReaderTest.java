package com.br.projeto.util;

import org.json.simple.JSONArray;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonReaderTest {

    @DisplayName("Testa se retorna um JsonArray")
    @Test
    void retornaArray(){
        String path = "src/main/resources/json/relacao_cidades.json"
        Assert.assertEquals(JSONArray.class, JsonReader.lerArquivoJson(path).getClass());
    }

}
