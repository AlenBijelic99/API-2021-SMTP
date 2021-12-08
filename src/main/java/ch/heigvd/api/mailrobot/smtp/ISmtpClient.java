package main.java.ch.heigvd.api.mailrobot.smtp;

import main.java.ch.heigvd.api.mailrobot.model.mail.Message;
import java.io.IOException;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * SMTP client interface
 */
public interface ISmtpClient {

    void sendMessage(Message message) throws IOException;
}
