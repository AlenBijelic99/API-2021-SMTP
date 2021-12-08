package main.java.ch.heigvd.api.mailrobot.model.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * Manage people
 */
public class Person {

    private String firstName;
    private String lastName;
    private final String address;

    /**
     * Person constructor
     * @param address Persons email address
     */
    public Person(String address) {
        this.address = address;
        Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
        Matcher matcher = pattern.matcher(address);
        boolean found = matcher.find();
        if(found){
            firstName = matcher.group(1);
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            lastName = matcher.group(2);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        }
    }

    /**
     * Get Persons first name
     * @return First name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get Persons last name
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get Persons email address
     * @return Email address
     */
    public String getAddress() {
        return address;
    }
}
