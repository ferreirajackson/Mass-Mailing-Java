/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enviador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Jackson Ferreira
 */
public class methods {

    public static void envia(String email) {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ferreirajackson.96@gmail.com", "pudncosdgpxixpwg");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ferreirajackson.96@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(email);

            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setHeader("content-type", "text/plain");
            message.setSubject("Teste - Enviando email com JavaMail");//Assunto
            /*
            String html = "Hi, how are you?\n"
                    + "I hope you are well!\n" + "\n\n"
                    + "My name is Jackson, originally from Brazil but living in Dublin for the past 2 years. As a\n"
                    + "Science Computing student qualified in ERP systems development and computer maintenance\n"
                    + "with 4 years of experience and many others relevant achievements during my career in Brazil,"
                    + "I am now looking for a job opportunity in Ireland where I intend to stablish my career."
                    + "I am willing to work as an intern, either paid or unpaid internships interest me, that`s because"
                    + "I believe that is an excelent opportunity to cooperate with the company growth"
                    + "and my pernsonal development, and when it comes to add knowledge and"
                    + "experience, the more the better."
                    + "My CV a with more detailed description of my skills is attached to this email for opportunities"
                    + "that match with my professional profile."
                    + "Thank you in advance for your time and I place myself available for further"
                    + "explanation."
                    + "Best Regards,"
                    + "Jackson Ferreira.";*/

            
            messageBodyPart1.setContent("vxcvx\nffdfdfsd \n fdsfd", "text/html; charset=UTF-8");

            message.getFileName();
            multipart.addBodyPart(messageBodyPart1);

            //Attachment - beginning
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            //messageBodyPart = new MimeBodyPart();
            String file = "C:\\Users\\Jackson Ferreira\\Desktop\\CV`s\\IT CV - Jackson Ferreira dos Santos.docx";
            String fileName = "IT CV - Jackson Ferreira dos Santos.docx";
            DataSource source = new FileDataSource(file);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart2);
            message.setContent(multipart);
            //End - Attachment
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void le() throws FileNotFoundException, IOException {
        String diretorio = "C:\\ArquivosCadastrosClientes\\Cadastrados";
        File file = new File(diretorio);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            // arquivos.toString(nome.getClass(arquivos));
            //System.out.println(arquivos.getName());
            String name = arquivos.getName();

            // pega o caminho e le o arquivo
            String nome = "C:\\ArquivosCadastrosClientes\\Cadastrados\\";
            nome = nome + name;

            BufferedReader br = new BufferedReader(new FileReader(nome));
            try {
                while (br.ready()) {
                    String linha = br.readLine();
                    envia(linha);
                    System.out.println(linha);
                }
            } catch (IOException ex) {
                Logger.getLogger(Enviador.class.getName()).log(Level.SEVERE, null, ex);
            }
            br.close();

            System.out.println("________________________________________");
        }
    }

}
