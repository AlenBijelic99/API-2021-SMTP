package main.java.ch.heigvd.api.mailrobot.model.mail;

public class Person {
    private final String lastname;
    private final String firstname;
    private final String email;

    public Person(String lastname, String firstname, String email){
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
