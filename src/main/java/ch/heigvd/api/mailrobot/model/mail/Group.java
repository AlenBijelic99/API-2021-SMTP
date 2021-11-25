package main.java.ch.heigvd.api.mailrobot.model.mail;

/*
    TODO:   Un groupe contient un liste de personne
            Il doit être possible d'ajouter des personnes
 */

import java.util.ArrayList;
import java.util.List;

public class Group {

    private final List<Person> members = new ArrayList<>();

    public void addMember(Person person){
        members.add(person);
    }

    public List<Person> getMembers() {
        return new ArrayList<>(members);
    }
}
