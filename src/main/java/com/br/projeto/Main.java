package com.br.projeto;
import com.br.projeto.calculadora.Calculadora;
import com.br.projeto.calculadora.MelhorFrotaCaminhoes;
import com.br.projeto.exeptions.CidadeInexistenteException;
import com.br.projeto.exeptions.OpcaoInvalidaException;
import com.br.projeto.pdf.Relatorio;
import com.br.projeto.pdf.RelatorioPDF;
import com.br.projeto.produtos.Produtos;
import com.br.projeto.tratamentoDadosEstatisticos.TratarDados;
import com.br.projeto.tratamentoDadosEstatisticos.Viagem;
import com.br.projeto.util.JsonReader;
import com.br.projeto.veiculos.CaminhoesHashMap;
import com.br.projeto.veiculos.Veiculo;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int opcao = 0;
        //listarCidades();

        while(opcao != 6){

            System.out.println("""
                Seja Bem Vindo a Trasportadora Amarelinha!!
                O que você deseja fazer:                              \s
                1 -  Consultar Trechos e Modalidades
                2 -  Cadastrar Transporte
                3 -  Dados Estatísticos
                4 -  Listar Cidades
                5 -  Listar Produtos
                6 -  Encerrar Programa
                Escolha sua Opção:
                """);
            try {
                opcao = scan.nextInt();
                if (opcao < 1 || opcao > 6)
                    throw new OpcaoInvalidaException("Opção inválida. Por favor, escolha uma opção valida (1 à 4)");
            } catch (OpcaoInvalidaException e){
                System.err.println(e.getMessage());
            }
            catch (InputMismatchException e) {
                System.err.println("Opção inválida. Por favor, escolha um número inteiro de (1 à 4)");
                scan.next();
            }
            switch (opcao){
                case 1 -> consultarTrechosModalidades();
                case 2 -> cadastrarTransporte();
                case 3 -> dadosEstatisticos();
                case 4 -> listarCidades();
                case 5 -> listarProdutos();
                case 6 -> System.out.println("Sair"); //Apenas
            }
        }



    }
    // Função para listar as cidades
    private static void listarCidades() {
        JSONArray jsonArray = JsonReader.lerArquivoJson("src/main/resources/json/relacao_cidades.json");
        jsonArray.forEach(obj -> {
            JSONObject cidade = (JSONObject) obj;
            System.out.println(cidade.get("CIDADE"));
        });
    }

    private static void listarProdutos(){
        Produtos produto = new Produtos();
        produto.getListaDeProdutos();
    }

    private static void consultarTrechosModalidades(){
        String tamanhoCaminhao;
        Calculadora calculadora = new Calculadora();

        recebeCidade(calculadora);

        System.out.println("Selecione o tamanho do caminhão: ");
        for (String tamanho : CaminhoesHashMap.hashMapVeiculos().keySet()){
            System.out.println(tamanho);
        }
        tamanhoCaminhao = scan.next();


        calculadora.setTamanhoCaminhao(tamanhoCaminhao);
        System.out.println("------------------------");
        System.out.println("Distância total: " + calculadora.getDistanciaEntreCidades() + " Km" );
        System.out.printf("Valor total estimado: R$ %.2f \n", calculadora.valorTotal());
        System.out.println("------------------------");




    }
    private static void cadastrarTransporte(){
        Produtos produtos = new Produtos();
        String opt = "";
        Calculadora calculadora = new Calculadora();
        double distanciaTotal, custoTotal, mediaUnitaria;
        List<Produtos> listProdutos;
        double pesoTotalProdutos = 0;
        int qtdTotal = 0;

        while(!Objects.equals(opt, "N") ){
            produtos.adicionarProduto();
            opt = loopContinuar(); //recebe a opcao que o usuario digitou S ou N
        }

        listProdutos = produtos.getProdutosList();

        for (Produtos produto : listProdutos){
            pesoTotalProdutos += produto.getPeso() * produto.getQuantidade();
            qtdTotal += produto.getQuantidade();
        }

        recebeCidade(calculadora);

        distanciaTotal = calculadora.getDistanciaEntreCidades();

        List<Veiculo> melhorCombinacao = MelhorFrotaCaminhoes.encontraMelhorFrota(CaminhoesHashMap.hashMapVeiculos(), pesoTotalProdutos, calculadora.getDistanciaEntreCidades());

        custoTotal = MelhorFrotaCaminhoes.menorCustoTotal;
        mediaUnitaria = (MelhorFrotaCaminhoes.menorCustoTotal / qtdTotal);

        System.out.println("----------------------");
        System.out.println("Distância total: " + distanciaTotal + " Km");
        System.out.printf("Peso total: %.2f Kg \n" , pesoTotalProdutos);
        System.out.printf("Custo total: R$ %.2f \n", custoTotal);
        System.out.printf("Preço unitário médio: R$ %.2f \n", mediaUnitaria );
        System.out.println("--------------------");
        System.out.println("Caminhões usados: ");
        for (Veiculo veiculo : melhorCombinacao){
            System.out.println(veiculo.getTipo());
        }
        System.out.println("--------------------");

        TratarDados.adicionarViagem(new Viagem(distanciaTotal, pesoTotalProdutos, custoTotal, mediaUnitaria, melhorCombinacao , qtdTotal, listProdutos));


    }
    private static void dadosEstatisticos(){


        Relatorio relatorio = new RelatorioPDF();

        System.out.println("==========================");
        System.out.println("||                      ||");
        System.out.println("||      RELATÓRIO       ||");
        System.out.println("||                      ||");
        System.out.println("==========================");
        TratarDados.custoPorTrecho();
        System.out.println("--------------------------");
        System.out.printf("\nCusto total das viagens: R$ %.2f \n" , TratarDados.custoTotalViagens());
        System.out.println("Número total de veículos utilizados: " + TratarDados.numeroTotalVeiculosTransportados());
        System.out.println("Número total de produtos transportados: " + TratarDados.numeroTotalProdutos());
        TratarDados.custoTotalPorModalidade();
        System.out.printf("\nCústo médio por Km: %.2f Km\n",  TratarDados.custoMedioPorKm());


        relatorio.gerarCabecalho();
        relatorio.imprimir();

        System.out.println("Também geramos um PDF com todas essas informações para você!!!");
        System.out.println("Você pode encontrar na pasta relatórios");

        enviaEmail();
    }

    private static String loopContinuar(){
        boolean controle = false;
        String opcao = "";

        while (!controle){
            controle = true;
            try {
                System.out.println("Deseja continuar? (s/n) ");
                opcao = scan.next().toUpperCase();
                if(!Objects.equals(opcao, "S") && !Objects.equals(opcao, "N")){
                    throw new OpcaoInvalidaException("Opção inválida!");
                }
            }catch (OpcaoInvalidaException e){
                System.err.println(e.getMessage());
                controle = false;
            }
        }
        return opcao;
    }


    private static void recebeCidade(Calculadora calculadora){
        String cidadeOrigem, cidadeDestino;
        boolean flag = false;
        scan.nextLine();
        while (!flag){
            try {

                System.out.println("----------------------");
                System.out.println("Digite a cidade de origem: ");
                cidadeOrigem = scan.nextLine();
                calculadora.setCidade(cidadeOrigem);

                System.out.println("Digite a cidade de destino: ");
                cidadeDestino = scan.nextLine();
                calculadora.setDestino(cidadeDestino);

                flag = true;
            }catch (CidadeInexistenteException e){

                System.err.println(e.getMessage());

            }

        }



    }

    private static void enviaEmail(){

        String meuEmail = "amarelinhaapp@gmail.com";
        String minhaSenha = "fazaprhtdqftvqbs";

        MultiPartEmail email = new MultiPartEmail();

        email.setHostName("smtp.gmail.com");
        email.setSSLOnConnect(true);
        email.setSmtpPort(465);
        email.setAuthentication(meuEmail, minhaSenha);


        try {
            email.setFrom(meuEmail);
            email.setSubject("Envio do relatório de Entregas");
            email.setMsg("Segue em anexo o relatório");
            email.addTo("vitornuneschagas2016@gmail.com");

            EmailAttachment anexo = new EmailAttachment();

            anexo.setPath("src/main/java/com/br/projeto/relatorios/RelatorioEntregas.pdf");

            anexo.setName("Arquivo_Relatório_Entregas.pdf");

            email.attach(anexo);

            email.send();
            System.out.println("E-mail enviado com sucesso!!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
