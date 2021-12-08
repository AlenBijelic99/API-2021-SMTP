package main.java.ch.heigvd.api.mailrobot.smtp;

import main.java.ch.heigvd.api.mailrobot.model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * Send emails to SMTP server
 */
public class SmtpClient implements ISmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private final String smtpServerAddress;
    private int smtpServerPort = 25;

    private Socket socket = null;
    private PrintWriter writer = null;
    private BufferedReader reader = null;

    /**
     * SmtpClient constructor
     * @param smtpServerAddress SMTP server address
     * @param port SMTP server port
     */
    public SmtpClient(String smtpServerAddress, int port) {
        this.smtpServerAddress = smtpServerAddress;
        smtpServerPort = port;
    }


    /**
     * Send email to SMTP server
     * @param message Message that will be sent to server
     */
    @Override
    public void sendMessage(Message message) {
        LOG.info("Sending message via SMTP");

        try {
            socket = new Socket(smtpServerAddress, smtpServerPort);

            try {
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.toString(), e);
                System.exit(-1);
            }

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
            writer.write("Content-Type: text/plain; charset=utf-8\r\n");
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

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (reader != null) reader.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }
}
