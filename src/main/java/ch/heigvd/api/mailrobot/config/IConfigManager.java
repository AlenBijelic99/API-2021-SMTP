package main.java.ch.heigvd.api.mailrobot.config;

/*
    TODO:   Définir les paramètres du serveur (addresse, port).
            Récupérer la liste des
 */


import java.util.List;

public interface IConfigManager {

    public static final String configFilePath = "../../../../../../../../../../../config/config.properties";
    public static final String messagesFilePath = "../../../../../../../../../../../config/messages.utf8";
    public static final String victimsFilePath = "../../../../../../../../../../../config/victims.utf8";

    // Functions for config.properties

    public String getSmtpServerAddress();

    public int getSmtpServerPort();

    public int getNumberOfGroups();

    public String getWitnessToCC();

    // Functions for messages.utf8
    public List<String> getMessagesList();

    // Functions for victims.utf8
    public List<String> getEmailsList();
}
