package com.br.projeto.pdf;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class RelatorioPDF implements Relatorio {

    private Document documento;
    private String caminhoRelatorio = "src/main/java/com/br/projeto/relatorios/RelatorioEntregas.pdf";

    public RelatorioPDF(){
        this.documento = new Document();
        try {
             PdfWriter.getInstance(this.documento, new FileOutputStream(caminhoRelatorio));
             this.documento.open();
        }catch (DocumentException | FileNotFoundException e){
            System.err.println("Nao foi possivel gerar o Relatorio");
        }

    }

    @Override
    public void gerarCabecalho() {
        Paragraph paragrafoTitulo = new Paragraph();

        paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
        paragrafoTitulo.add(new Chunk("RELATORIO DE ENTREGAS", new Font(Font.HELVETICA, 24)));

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
    }

    @Override
    public void gerarConteudo() {

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
