package com.br.projeto.validacoes.caminhoes;

import com.br.projeto.exeptions.CidadeInexistenteException;
import com.br.projeto.veiculos.CaminhoesHashMap;


public class ValidaCaminhao {
    public boolean validadorTipoCaminhao(String caminhao) {
        boolean flag = false;

        try {
            while(!flag){
                for (String tamanho : CaminhoesHashMap.hashMapVeiculos().keySet()){
                    if (tamanho.equals(caminhao.toUpperCase())) {
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    throw new CidadeInexistenteException("Caminhão Inválido!"); //se for falso, retorna uma exception
                }
            }
        }catch (NullPointerException e){
            System.err.println("Campos inválidos! Digite os campos corretamente!");
        }

        return flag;
    }
}
