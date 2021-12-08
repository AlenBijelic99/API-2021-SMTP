package main.java.ch.heigvd.api.mailrobot.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * Manage Person groups
 */
public class Group {

    private final List<Person> members = new ArrayList<>();

    /**
     * Add a person to the list
     * @param person Person to add in the group
     */
    public void addMember(Person person){
        members.add(person);
    }

    /**
     * Get a list of all people in the group
     * @return List of people in the group
     */
    public List<Person> getMembers() {
        return new ArrayList<>(members);
    }
}
