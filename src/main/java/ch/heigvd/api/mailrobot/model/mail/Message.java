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
    private final String from;
    private final String[] to;
    private final String[] cc;
    private final String[] bcc;
    private final String subject;
    private final String body;

    public Message(String from, String[] to, String[] cc, String[] bcc, String subject, String body){
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", bcc=" + Arrays.toString(bcc) +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
