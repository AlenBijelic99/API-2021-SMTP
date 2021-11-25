package main.java.ch.heigvd.api.mailrobot.model.mail;


/*
    TODO:   Un message correspond au message smtp qui sera envoyer
            Il contient donc:
                - Un expediteur (from)
                - Des destinataires (to)
                - Des emails en copie (cc)
                - Des email en copie cachée (bcc)
                - Le sujet de l'email (subject)
                - Le corps de l'email (body)
 */

import java.util.Arrays;

public class Message {

    private String from;
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];
    private String subject;
    private String body;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
