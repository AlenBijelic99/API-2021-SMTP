package main.java.ch.heigvd.api.mailrobot.smtp;

import main.java.ch.heigvd.api.mailrobot.model.mail.Message;

import java.io.IOException;

public interface ISmtpClient {

    public void sendMessage(Message message) throws IOException;
}
