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
    public String getSmtpServerAddress();

    public int getSmtpServerPort();

    public int getNumberOfGroups();

    public String getWitnessToCC();

    // Functions for messages.utf8
    public List<String> laodMessagesFromFile();

    // Functions for victims.utf8
    public List<String> loadAddressFromFile();
}
