package com.br.projeto;
import com.br.projeto.exeptions.OpcaoInvalidaException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("""
                Bem Vindo!!
                Menu                              \s
                1 -  Consultar Trechos e Modalidades
                2 -  Cadastrar transporte
                3 -  Dados estatísticos
                4  - Encerrar Programa
                Escolha sua Opção:
                """);
            try {
                option = scanner.nextInt();
                if (option < 1 || option > 4)
                    throw new OpcaoInvalidaException("Opcao invalida. Por favor, escolha uma opcao valida (1 a 4)");
            } catch (OpcaoInvalidaException e){
                System.err.println(e.getMessage());
            }
            catch (InputMismatchException e) {
                System.err.println("Opcao invalida. Por favor, escolha uma opcao valida (1 a 4)");
                scanner.next();
            }
        }while ( option < 1 ||  option > 4);
    }
}
