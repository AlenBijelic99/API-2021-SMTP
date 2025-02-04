package main.java.ch.heigvd.api.mailrobot.model.prank;

import main.java.ch.heigvd.api.mailrobot.config.IConfigManager;
import main.java.ch.heigvd.api.mailrobot.model.mail.Group;
import main.java.ch.heigvd.api.mailrobot.model.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * Generates all pranks
 */
public class PrankGenerator {

    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    private final IConfigManager configManager;

    /**
     * PrankGenerator constructor
     * @param configManager Manager to retrieve config infos
     */
    public PrankGenerator(IConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Generates pranks
     * @return A list of all pranks
     */
    public List<Prank> generatePranks(){

        List<Prank> pranks = new ArrayList<>();
        List<String> messages = configManager.loadMessagesFromFile();
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

    /**
     * Generates a list of group of people
     * @param adresseVictims Victims address
     * @param numberOfGroups Number of groups
     * @return A list of groups
     */
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
}
