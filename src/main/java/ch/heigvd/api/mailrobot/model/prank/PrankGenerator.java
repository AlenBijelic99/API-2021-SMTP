package main.java.ch.heigvd.api.mailrobot.model.prank;

/*
    TODO:   Logique du programme.
            Prépare tout pour envoyer des pranks
            Définir les règles:
                - Min. 3 personnes par groupes (1 sender et 2 recipients)
                - Le nombre de groupe de victimes
 */

import main.java.ch.heigvd.api.mailrobot.config.IConfigManager;
import main.java.ch.heigvd.api.mailrobot.model.mail.Group;
import main.java.ch.heigvd.api.mailrobot.model.mail.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


public class PrankGenerator {

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    private IConfigManager configManager;

    public PrankGenerator(IConfigManager configManager) {
        this.configManager = configManager;
    }

    public List<Prank> generatePranks(){

        List<Prank> pranks = new ArrayList<>();
        List<String> messages = configManager.laodMessagesFromFile();
        int messageIndex = 0;

        int numberOfGroups = configManager.getNumberOfGroups();
        int numberOfVictims = configManager.loadAddressFromFile().size();

        if(numberOfVictims / numberOfGroups < 3){
            numberOfGroups = numberOfVictims / 3;
            LOG.warning("There are not enough victims to generate the desired number of groups.");
        }

        List<Group> groups = generateGroups(configManager.loadAddressFromFile(), numberOfGroups);
        for(Group group : groups){
            Prank prank = new Prank();

            List<Person> victims = group.getMembers();
            Collections.shuffle(victims);
            Person sender = victims.remove(0);
            prank.setVictimSender(sender);
            prank.addVictimRecipients(victims);
            prank.addWitnessRecipients(configManager.getWitnessToCC());

            String message = messages.get(messageIndex);
            messageIndex = (messageIndex + 1) % messages.size();
            prank.setMessage(message);

            pranks.add(prank);
        }
        return pranks;
    }

    private List<Group> generateGroups(List<String> adresseVictims, int numberOfGroups){
        List<String> availableVictims = new ArrayList<>(adresseVictims);
        Collections.shuffle(availableVictims);
        List<Group> groups = new ArrayList<>();
        for(int i = 0; i < numberOfGroups; i++){
            Group group = new Group();
            groups.add(group);
        }
        int turn = 0;
        Group targetGroup;
        while(availableVictims.size() > 0){
            targetGroup = groups.get(turn);
            turn = (turn + 1) % groups.size();
            Person victim = new Person(availableVictims.remove(0));
            targetGroup.addMember(victim);
        }
        return groups;
    }

    private void generateGroupFile(List<Prank> pranks) throws IOException {
        File out = new File("target\\out");
        if(!out.exists()){
            Files.createDirectories(Paths.get("target\\out"));
        }else{
            File[] outFiles = out.listFiles();
            if(outFiles != null){
                for(File f : outFiles){
                    f.delete();
                }
            }
        }

        PrintWriter writer = new PrintWriter(new FileWriter(out.getPath() + "\\groups.utf8", StandardCharsets.UTF_8));

        StringBuilder strBuild = new StringBuilder();

        for(Prank p : pranks){
            strBuild.append("From ").append(p.getVictimSender().getAddress()).append("\n");
            strBuild.append("To:").append("\n");
            for(Person person : p.getVictimRecipients()){
                strBuild.append("\t").append(person.getAddress()).append("\n");
            }
            strBuild.append("--");
        }

        writer.println(strBuild);
        writer.flush();
        writer.close();
    }

}
