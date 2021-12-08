package main.java.ch.heigvd.api.mailrobot.model.mail;

/**
 * @author Alen Bijelic
 * @author Stefano Pontarolo
 * Email message information
 */
public class Message {

    private String from;
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];
    private String body;

    /**
     * Get from string address
     * @return From address to string
     */
    public String getFrom() {
        return from;
    }

    /**
     * Set from address
     * @param from From address
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Get to string array address
     * @return String array of address
     */
    public String[] getTo() {
        return to;
    }

    /**
     * Set to string address
     * @param to Array of recipients address
     */
    public void setTo(String[] to) {
        this.to = to;
    }

    /**
     * Get copy recipients string array
     * @return Copy recipients array
     */
    public String[] getCc() {
        return cc;
    }

    /**
     * Set copy recipients string array
     * @param cc Copy recipients array
     */
    public void setCc(String[] cc) {
        this.cc = cc;
    }

    /**
     * Get hidden copy recipients array
     * @return Hidden copy recipients array
     */
    public String[] getBcc() {
        return bcc;
    }

    /**
     * Get body of the message
     * @return Message body
     */
    public String getBody() {
        return body;
    }

    /**
     * Set body of a message
     * @param body Message body
     */
    public void setBody(String body) {
        this.body = body;
    }
}
