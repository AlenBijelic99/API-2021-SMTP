package main.java.ch.heigvd.api.mailrobot.config;


/*
    TODO: Lire les fichiers de configuration
                - Lire les informations du serveur SMTP (il existe des classe permettant de faire le parsing des fihiers properties, faire des recherches)
                - Lire la liste des victimes (Lire chaque ligne = readLine) (loadAddressFromFile)
                - Lire les messages. Un message se termine avec les caractères ==. (laodMessagesFromFile)
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class ConfigManager implements IConfigManager{

    private String readFromPropertyFile(String propertyName){
        try {
            InputStream input = new FileInputStream(configFilePath);

            Properties property = new Properties();

            property.load(input);

            return property.getProperty(propertyName);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSmtpServerAddress() {
        return Objects.requireNonNull(readFromPropertyFile("smtpServerAddress"));
    }

    @Override
    public int getSmtpServerPort() {
        return Integer.parseInt(Objects.requireNonNull(readFromPropertyFile("smtpServerPort")));
    }

    @Override
    public int getNumberOfGroups() {
        return Integer.parseInt(Objects.requireNonNull(readFromPropertyFile("numberOfGroups")));
    }

    @Override
    public String getWitnessToCC() {
        return Objects.requireNonNull(readFromPropertyFile("witnessToCC"));
    }

    @Override
    public List<String> getEmailsList() {
        return null;
    }

    @Override
    public List<String> getMessagesList() {
        return null;
    }

    public static void main(String[] args) {
        ConfigManager configManager = new ConfigManager();
        System.out.println(configManager.getSmtpServerAddress());
    }
}
