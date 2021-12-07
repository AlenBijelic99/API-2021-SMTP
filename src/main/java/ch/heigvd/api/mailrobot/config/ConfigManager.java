package main.java.ch.heigvd.api.mailrobot.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    public List<String> laodMessagesFromFile() {
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
