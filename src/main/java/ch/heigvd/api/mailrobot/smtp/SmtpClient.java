package main.java.ch.heigvd.api.mailrobot.smtp;

/*
    TODO: Se connecter au serveur SMTP à l'aide de socket et obtenir les flux d'entrée et de sortie
            - On attend que le serveur envoie une première ligne
            - On envoie une première commande
            - On lit la/les lignes que le serveur envoie en respectant le dialogue SMTP (La dernière ligne ne contient pas de '-' entre le code et le message. Exemple "250 HELP". cf page 18 des slides)
            - Fonction pour envoyer un message (expediteur, recepteur, subject, message)
 */


import main.java.ch.heigvd.api.mailrobot.model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private String smtpServerAddress;
    private int smtpServerPort = 25;

    private Socket socket = null;
    private PrintWriter writer = null;
    private BufferedReader reader = null;

    public SmtpClient(String smtpServerAddress, int port) {
        this.smtpServerAddress = smtpServerAddress;
        smtpServerPort = port;
    }

    
    @Override
    public void sendMessage(Message message) throws IOException {
        LOG.info("Sending message via SMTP");
        Socket socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String line = reader.readLine();
        LOG.info(line);
        writer.printf("EHLO localhost\r\n");
        line = reader.readLine();
        LOG.info(line);
        if(!line.startsWith("250")){
            throw new IOException("SMTP error: " + line);
        }
        while(line.startsWith("250-")){
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("MAIL FROM:");
        writer.write(message.getFrom());
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        for(String to : message.getTo()){
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for(String to : message.getCc()){
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for (String to : message.getBcc()) {
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("DATA");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");
        writer.write("From: " + message.getFrom() + "\r\n");

        writer.write("To: " + message.getTo()[0]);
        for(int i = 1; i < message.getTo().length; i++){
            writer.write(", " + message.getTo()[i]);
        }
        writer.write("\r\n");

        writer.write("Cc: " + message.getCc()[0]);
        for(int i = 1; i < message.getCc().length; i++){
            writer.write(", " + message.getCc()[i]);
        }
        writer.write("\r\n");

        writer.flush();
        LOG.info(message.getBody());
        writer.write(message.getBody());
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("QUIT\r\n");
        writer.flush();
        reader.close();
        writer.close();
        socket.close();
    }

    public static void main(String[] args) {
        SmtpClient smtpClient = new SmtpClient("locahost", 2525);
        Message message = new Message();
        message.setFrom("alen.bijelic@heig-vd.ch");
        String[] to = {"test@test.com"};
        message.setTo(to);

        try{
            smtpClient.sendMessage(message);
        }
        catch (IOException ex){
           ex.printStackTrace();
        }
    }
}
