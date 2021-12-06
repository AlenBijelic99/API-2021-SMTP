package main.java.ch.heigvd.api.mailrobot;

import main.java.ch.heigvd.api.mailrobot.config.ConfigManager;
import main.java.ch.heigvd.api.mailrobot.model.prank.PrankGenerator;

public class MailRobot {
    public static void main(String[] args) {
        ConfigManager configManager = new ConfigManager();
        PrankGenerator prankGenerator = new PrankGenerator(configManager);
        prankGenerator.generatePranks();
    }
}
