package main.java.ch.heigvd.api.mailrobot;

import main.java.ch.heigvd.api.mailrobot.config.ConfigManager;
import main.java.ch.heigvd.api.mailrobot.model.prank.Prank;
import main.java.ch.heigvd.api.mailrobot.model.prank.PrankGenerator;
import main.java.ch.heigvd.api.mailrobot.smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

public class MailRobot {
    public static void main(String[] args) throws IOException {
        ConfigManager configManager = new ConfigManager();
        PrankGenerator prankGenerator = new PrankGenerator(configManager);
        List<Prank> pranks = prankGenerator.generatePranks();

        String serverAddress = configManager.getSmtpServerAddress();
        int serverPort = configManager.getSmtpServerPort();
        int nbGroup = configManager.getNumberOfGroups();

        SmtpClient smtp = new SmtpClient(serverAddress, serverPort);

        for(Prank p : pranks){
            smtp.sendMessage(p.generateMailMessage());
        }
    }
}
