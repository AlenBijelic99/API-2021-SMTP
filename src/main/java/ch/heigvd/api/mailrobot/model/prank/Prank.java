package main.java.ch.heigvd.api.mailrobot.model.prank;

import main.java.ch.heigvd.api.mailrobot.model.mail.Message;
import main.java.ch.heigvd.api.mailrobot.model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * Manage Pranks
 */
public class Prank {

    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();
    private String message;

    /**
     * Set the victim sender
     * @param victimSender Victim sender
     */
    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    /**
     * Set prank message
     * @param message Prank message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Add a victim recipient to the list
     * @param victims A list of victims to add in the victim recipients
     */
    public void addVictimRecipients(List<Person> victims){
        victimRecipients.addAll(victims);
    }

    /**
     * Add a witness recipient to the list
     * @param witness Witness to add in the list
     */
    public void addWitnessRecipients(String witness){
        witnessRecipients.add(new Person(witness));
    }

    /**
     * Generate email Message
     * @return Email Message
     */
    public Message generateMailMessage(){
        Message msg = new Message();

        msg.setBody(this.message + "\r\n" + victimSender.getFirstName());

        String[] to = victimRecipients.stream().map(Person::getAddress).collect(Collectors.toList()).toArray(new String[]{});
        msg.setTo(to);

        String[] cc = witnessRecipients.stream().map(Person::getAddress).collect(Collectors.toList()).toArray(new String[]{});
        msg.setCc(cc);

        msg.setFrom(victimSender.getAddress());

        return msg;
    }
}
