package main.java.ch.heigvd.api.mailrobot.model.prank;

/*
    TODO: Un prank contient une personne qui envoie le prank, une liste de victimes (victimsRecipients), une liste de témoins (witnessRecipients) et un message
 */

import main.java.ch.heigvd.api.mailrobot.model.mail.Message;
import main.java.ch.heigvd.api.mailrobot.model.mail.Person;

import java.util.ArrayList;
import java.util.List;

public class Prank {
    private Person sender;
    private List<Person> victimsRecipients;
    private List<Person> witnessRecipients;
    private Message message;

    public Prank(Person sender, List<Person> victimsRecipients, List<Person> witnessRecipients, Message message){
        this.sender = sender;
        this.victimsRecipients = new ArrayList<>(victimsRecipients);
        this.witnessRecipients = new ArrayList<>(witnessRecipients);
        this.message = message;
    }

    @Override
    public String toString() {
        return "Prank{" +
                "sender=" + sender +
                ", victimsRecipients=" + victimsRecipients +
                ", witnessRecipients=" + witnessRecipients +
                ", message=" + message +
                '}';
    }


    // TODO: Remove main. Juste here for testing
    public static void main(String[] args){
        Person sender = new Person("Bijelic", "Alen", "alen.bijelic@heig-vd.ch");
        Person victim = new Person("Lange", "Yanik", "Yanik.Lange@heig-vd.ch");
        List<Person> victimsList = new ArrayList<>();
        List<Person> witnessList = new ArrayList<>();
        victimsList.add(victim);
        String[] victims = {victim.getEmail()};
        Message messageEmail = new Message(sender.getEmail(), victims, null, null, "Hello", "Body");
        Prank prank = new Prank(sender, victimsList, witnessList, messageEmail);
        System.out.println(prank);
    }
}
