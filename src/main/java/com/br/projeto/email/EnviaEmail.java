package com.br.projeto.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

public class EnviaEmail {
    public void enviarEmail(){

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
            email.addTo("perquimrobert@gmail.com");
            email.addTo("moisesbricenomedina29@gmail.com");
            email.addTo("gabrielkretzmanndasilva@hotmail.com");
            EmailAttachment anexo = new EmailAttachment();

            anexo.setPath("src/main/java/com/br/projeto/relatorios/RelatorioEntregas.pdf");

            anexo.setName("Arquivo_Relatorio_Entregas.pdf");

            email.attach(anexo);

            email.send();
            System.out.println("Enviamos o relatorio para seu e-mail!!!!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
