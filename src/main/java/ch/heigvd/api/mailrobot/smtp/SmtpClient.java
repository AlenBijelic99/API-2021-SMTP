package main.java.ch.heigvd.api.mailrobot.smtp;

/*
    TODO: Se connecter au serveur SMTP à l'aide de socket et obtenir les flux d'entrée et de sortie
            - On attend que le serveur envoie une première ligne
            - On envoie une première commande
            - On lit la/les lignes que le serveur envoie en respectant le dialogue SMTP (La dernière ligne ne contient pas de '-' entre le code et le message. Exemple "250 HELP". cf page 18 des slides)
            - Fonction pour envoyer un message (expediteur, recepteur, subject, message)
 */


import main.java.ch.heigvd.api.mailrobot.model.mail.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {

    private final static Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private final String smtpServerAddress;
    private int smtpServerPort = 25;

    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public SmtpClient(String smtpServerAddress, int port){
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = port;
    }

    
    @Override
    public void sendMessage(Message message) throws IOException {
        LOG.log(Level.INFO, "Connecting to SMTP server");


    }
}
