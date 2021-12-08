package main.java.ch.heigvd.api.mailrobot.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * ConfigManager retrieve config infos
 */
public class ConfigManager implements IConfigManager{

    /**
     * Reads in specified config file
     * @param propertyName Property to search in .properties file
     * @return String of the value
     */
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

    /**
     * Get SMTP server address
     * @return SMTP server address
     */
    @Override
    public String getSmtpServerAddress() {
        return Objects.requireNonNull(readFromPropertyFile("smtpServerAddress"));
    }

    /**
     * Get SMTP server port
     * @return SMTP server port
     */
    @Override
    public int getSmtpServerPort() {
        return Integer.parseInt(Objects.requireNonNull(readFromPropertyFile("smtpServerPort")));
    }

    /**
     * Get number of groups
     * @return Number of groups
     */
    @Override
    public int getNumberOfGroups() {
        return Integer.parseInt(Objects.requireNonNull(readFromPropertyFile("numberOfGroups")));
    }

    /**
     * Get witness CC email address
     * @return Witness CC email address
     */
    @Override
    public String getWitnessToCC() {
        return Objects.requireNonNull(readFromPropertyFile("witnessToCC"));
    }

    /**
     * Read all messages from messages.utf8 file
     * @return List of all messages
     */
    public List<String> loadMessagesFromFile() {
        List<String> messagesList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(messagesFilePath), StandardCharsets.UTF_8));

            String line;
            StringBuilder stringbuilder = new StringBuilder();
            while((line = bufferedReader.readLine()) != null){
                if(line.startsWith("==")){
                    messagesList.add(stringbuilder.toString());
                    stringbuilder = new StringBuilder();
                }else{
                    stringbuilder.append(line).append("\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return messagesList;
    }

    /**
     * Read all email address from victims.utf8 file
     * @return List of victims email address
     */
    @Override
    public List<String> loadAddressFromFile() {
        List<String> emailList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(victimsFilePath));

            String line;
            while((line = bufferedReader.readLine()) != null){
                emailList.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return emailList;
    }
}
