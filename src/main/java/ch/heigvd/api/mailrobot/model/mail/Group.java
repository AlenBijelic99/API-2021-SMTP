package main.java.ch.heigvd.api.mailrobot.model.mail;

/*
    TODO:   Un groupe contient un liste de personne
            Il doit être possible d'ajouter des personnes
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group {
    public List<Person> personList = new ArrayList<>();

    public Group(Person ... people){
        personList.addAll(Arrays.asList(people));
    }

    /**
     *
     * @param person
     */
    public void add(Person person){
        personList.add(person);
    }

    @Override
    public String toString() {
        return "Group{" +
                "personList=" + personList +
                '}';
    }
}
