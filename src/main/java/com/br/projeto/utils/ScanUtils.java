package com.br.projeto.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Scanner;

public class ScanUtils {
    private Scanner scanner = new Scanner(System.in);

    public String coletaTexto(String mensagem) {
        System.out.println(mensagem);
        String result = "";
        do {
            result = scanner.nextLine();
        } while (result == null || result.equals(""));
        return result;
    }

    public double coletaDouble(String mensagem) {
        System.out.println(mensagem);
        try {
            return scanner.nextDouble();
        } catch (Exception e) {
            scanner.next();
            System.out.println(MENSAGENS.VALOR_INVALIDO);
            return coletaDouble(mensagem);
        }
    }

    public int coletaInt(String mensagem) {
        System.out.println(mensagem);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.next();
            System.out.println(MENSAGENS.VALOR_INVALIDO);
            return coletaInt(mensagem);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface MENSAGENS {
        String VALOR_INVALIDO = "Inválido, informe novamente.";
    }
}