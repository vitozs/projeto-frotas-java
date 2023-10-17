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
        paragrafoTitulo.add(new Chunk("RELATORIO DE ENTREGAS", new Font(Font.HELVETICA, 24)));

        this.documento.add(paragrafoTitulo);

        pulaLinha();

        Paragraph paragraTransportadora = new Paragraph();

        paragraTransportadora.setAlignment(Element.ALIGN_CENTER);
        paragraTransportadora.add(new Chunk("Transportadora Amarelinha", new Font(Font.BOLD, 16)));

        this.documento.add(paragraTransportadora);

        pulaLinha();
        pulaLinha();

        Paragraph paragrafoDivisao = new Paragraph("----------------------------------------------------------------");
        paragrafoDivisao.setAlignment(Element.ALIGN_CENTER);

        pulaLinha();
        pulaLinha();

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
            list.add("Distancia total: " + df.format(viagem.getDistanciaTotal()) + " Km");
            list.add("Peso total: " + df.format(viagem.getPesoTotal()) + " Kg");
            list.add("Custo total: R$ " + df.format(viagem.getCustoTotal()));

            for (Map.Entry<Veiculo, Long> entry : veiculoMap.entrySet()) {
                 veiculos += " " + entry.getValue().toString() + " " + entry.getKey().getTipo();
            }
            list.add("Veiculos usados: " + veiculos);
            list.add("Produtos: " +  viagem.getListaProdutos().stream().map(v -> v.getNome() + " ").collect(Collectors.joining()));
            list.add("Qtd. Total de produtos: " +  viagem.getQtdTotalProdutos());
            list.add("Preco unitario medio: R$ " + df.format(viagem.getPrecoUnitario()));
            documento.add(list);
            pulaLinha();
            pulaLinha();

        }



        Paragraph paragrafoCustoTotal = new Paragraph();

        paragrafoCustoTotal.setAlignment(Element.ALIGN_LEFT);
        paragrafoCustoTotal.add(new Chunk("CUSTO TOTAL: ", new Font(Font.BOLD, 16)));
        documento.add(paragrafoCustoTotal);
        pulaLinha();

        documento.add(new Paragraph("Custo total: R$ " + df.format(TratarDados.custoTotalViagens())));
        documento.add(new Paragraph("Total de veiculos usados: " + TratarDados.numeroTotalVeiculosTransportados()));
        documento.add(new Paragraph("Total de produtos transportados: " + TratarDados.numeroTotalProdutos()));
        documento.add(new Paragraph("Total por modalidade: "));
        com.lowagie.text.List listaModalidade = new com.lowagie.text.List();
        for(Viagem viagem : viagens){
            for (Veiculo veiculo : viagem.getCombinacaoVeiculos()){
                listaModalidade.add(veiculo.getTipo() + ": R$ " + df.format(veiculo.getCustoPorKm() * viagem.getDistanciaTotal()));
            }
        }
        documento.add(listaModalidade);
        documento.add(new Paragraph("Custo medio por Km: R$ " + df.format(TratarDados.custoMedioPorKm())));

        pulaLinha();
        pulaLinha();
        pulaLinha();
        pulaLinha();
        pulaLinha();
        pulaLinha();

        gerarRodape();

    }

    @Override
    public void gerarRodape() {
        Paragraph rodape = new Paragraph();

        rodape.setAlignment(Element.ALIGN_CENTER);
        rodape.add(new Chunk("Desenvolvido por: ", new Font(Font.HELVETICA, 14)));
        documento.add(rodape);

        Paragraph gabriel = new Paragraph();

        gabriel.setAlignment(Element.ALIGN_CENTER);
        gabriel.add(new Chunk("Gabriel Kretzmann", new Font(Font.HELVETICA, 10)));
        documento.add(gabriel);

        Paragraph iago = new Paragraph();

        iago.setAlignment(Element.ALIGN_CENTER);
        iago.add(new Chunk("Iago Cappeletti", new Font(Font.HELVETICA, 10)));
        documento.add(iago);
        Paragraph moises = new Paragraph();

        moises.setAlignment(Element.ALIGN_CENTER);
        moises.add(new Chunk("Moises Briceno", new Font(Font.HELVETICA, 10)));
        documento.add(moises);

        Paragraph robert = new Paragraph();

        robert.setAlignment(Element.ALIGN_CENTER);
        robert.add(new Chunk("Robert Perquim", new Font(Font.HELVETICA, 10)));
        documento.add(robert);

        Paragraph vitor = new Paragraph();

        vitor.setAlignment(Element.ALIGN_CENTER);
        vitor.add(new Chunk("Vitor Nunes", new Font(Font.HELVETICA, 10)));
        documento.add(vitor);

        imprimir();
    }

    private void pulaLinha(){
        this.documento.add(new Paragraph(" "));
    }

    @Override
    public void imprimir() {
        if(this.documento != null && this.documento.isOpen()){
            this.documento.close();
        }
    }
}
