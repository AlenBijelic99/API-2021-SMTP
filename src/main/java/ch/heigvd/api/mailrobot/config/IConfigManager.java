package main.java.ch.heigvd.api.mailrobot.config;

import java.util.List;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * Config Manager Interface
 */
public interface IConfigManager {

    String currentProjectFilePath = System.getProperty("user.dir");

    String configFilePath = currentProjectFilePath + "/config/config.properties";
    String messagesFilePath = currentProjectFilePath + "/config/messages.utf8";
    String victimsFilePath = currentProjectFilePath + "/config/victims.utf8";

    // Functions for config.properties
    String getSmtpServerAddress();

    int getSmtpServerPort();

    int getNumberOfGroups();

    String getWitnessToCC();

    // Functions for messages.utf8
    List<String> loadMessagesFromFile();

    // Functions for victims.utf8
    List<String> loadAddressFromFile();
}
