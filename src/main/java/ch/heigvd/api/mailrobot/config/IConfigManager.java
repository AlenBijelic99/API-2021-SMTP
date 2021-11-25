package main.java.ch.heigvd.api.mailrobot.config;

import java.util.List;

public interface IConfigManager {

    public final String currentProjectFilePath = System.getProperty("user.dir");

    public static final String configFilePath = currentProjectFilePath + "/config/config.properties";
    public static final String messagesFilePath = currentProjectFilePath + "/config/messages.utf8";
    public static final String victimsFilePath = currentProjectFilePath + "/config/victims.utf8";

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
