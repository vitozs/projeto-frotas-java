package com.br.projeto.pdf;


import com.br.projeto.tratamentoDadosEstatisticos.TratarDados;
import com.br.projeto.tratamentoDadosEstatisticos.Viagem;
import com.br.projeto.veiculos.Veiculo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import  java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioPDF implements Relatorio {

    private Document documento;
    private String caminhoRelatorio = "src/main/java/com/br/projeto/relatorios/RelatorioEntregas.pdf";

    public RelatorioPDF(){
        this.documento = new Document();
        try {
             PdfWriter.getInstance(this.documento, new FileOutputStream(caminhoRelatorio));
             this.documento.open();
        }catch (DocumentException | FileNotFoundException e){
            System.err.println("Não foi possível gerar o Relatório");
        }

    }

    @Override
    public void gerarCabecalho() {
        Paragraph paragrafoTitulo = new Paragraph();

        paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
        paragrafoTitulo.add(new Chunk("RELATÓRIO DE ENTREGAS", new Font(Font.HELVETICA, 24)));

        this.documento.add(paragrafoTitulo);

        this.documento.add(new Paragraph(" "));

        Paragraph paragraTransportadora = new Paragraph();

        paragraTransportadora.setAlignment(Element.ALIGN_CENTER);
        paragraTransportadora.add(new Chunk("Transportadora Amarelinha", new Font(Font.BOLD, 16)));

        this.documento.add(paragraTransportadora);

        this.documento.add(new Paragraph(" "));
        this.documento.add(new Paragraph(" "));
        
        Paragraph paragrafoDivisao = new Paragraph("----------------------------------------------------------------");
        paragrafoDivisao.setAlignment(Element.ALIGN_CENTER);

         this.documento.add(new Paragraph(" "));
         this.documento.add(new Paragraph(" "));

         gerarConteudo();

    }

    @Override
    public void gerarConteudo() {

        Paragraph paragrafoCustoTrecho = new Paragraph();

        paragrafoCustoTrecho.setAlignment(Element.ALIGN_LEFT);
        paragrafoCustoTrecho.add(new Chunk("CUSTO POR TRECHO: ", new Font(Font.BOLD, 16)));
        documento.add(paragrafoCustoTrecho);
        documento.add(new Paragraph(" "));
        List<Viagem> viagens = TratarDados.getViagens();
        DecimalFormat df = new DecimalFormat("#,###.##",
                new DecimalFormatSymbols(Locale.GERMAN));
        for(Viagem viagem : viagens){
            List<Veiculo> listaVeiculos = viagem.getCombinacaoVeiculos();
            Map<Veiculo, Long> veiculoMap = listaVeiculos.stream().collect(Collectors.groupingBy(v -> v, Collectors.counting()));
            String veiculos = "";
            this.documento.add(new Paragraph("TRECHO " + (viagens.indexOf(viagem)+1), new Font(Font.BOLD, 14)));
            com.lowagie.text.List list = new com.lowagie.text.List();
            list.add("Cidade de origem: " + viagem.getCidadeOrigem());
            list.add("Cidade de destino: " + viagem.getCidadeDestino());
            list.add("Distância total: " + df.format(viagem.getDistanciaTotal()) + " Km");
            list.add("Peso total: " + df.format(viagem.getPesoTotal()) + " Kg");
            list.add("Custo total: R$ " + df.format(viagem.getCustoTotal()));

            for (Map.Entry<Veiculo, Long> entry : veiculoMap.entrySet()) {
                 veiculos += " " + entry.getValue().toString() + " " + entry.getKey().getTipo();
            }
            list.add("Veículos usados: " + veiculos);
            list.add("Produtos: " +  viagem.getListaProdutos().stream().map(v -> v.getNome() + " ").collect(Collectors.joining()));
            list.add("Qtd. Total de produtos: " +  viagem.getQtdTotalProdutos());
            list.add("Preço unitário medio: R$ " + df.format(viagem.getPrecoUnitario()));
            documento.add(list);
            this.documento.add(new Paragraph(" "));
            this.documento.add(new Paragraph(" "));
        }



        Paragraph paragrafoCustoTotal = new Paragraph();

        paragrafoCustoTotal.setAlignment(Element.ALIGN_LEFT);
        paragrafoCustoTotal.add(new Chunk("CUSTO TOTAL: ", new Font(Font.BOLD, 16)));
        documento.add(paragrafoCustoTotal);
        documento.add(new Paragraph(" "));

        documento.add(new Paragraph("Custo total: R$ " + df.format(TratarDados.custoTotalViagens())));
        documento.add(new Paragraph("Total de veículos usados: " + TratarDados.numeroTotalVeiculosTransportados()));
        documento.add(new Paragraph("Total de produtos transportados: " + TratarDados.numeroTotalProdutos()));
        documento.add(new Paragraph("Total por modalidade: "));
        com.lowagie.text.List listaModalidade = new com.lowagie.text.List();
        for(Viagem viagem : viagens){
            for (Veiculo veiculo : viagem.getCombinacaoVeiculos()){
                listaModalidade.add(veiculo.getTipo() + ": R$ " + df.format(veiculo.getCustoPorKm() * viagem.getDistanciaTotal()));
            }
        }
        documento.add(listaModalidade);
        documento.add(new Paragraph("Custo médio por Km: R$ " + df.format(TratarDados.custoMedioPorKm())));



    }

    @Override
    public void gerarRodape() {

    }

    @Override
    public void imprimir() {
        if(this.documento != null && this.documento.isOpen()){
            this.documento.close();
        }
    }
}
