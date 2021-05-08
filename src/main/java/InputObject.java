import java.util.List;

/**
 * Input object holds receiving Kingdom object and secret message sent to that kingdom by King Shan of kingdom Space.
 * Output object holds allegiance boolean and kingdom receiving secret message.
 */

public class InputObject {
    private final String receivingKingdom;
    private final List<Integer> secretMessage;

    public InputObject(String receivingKingdom, List<Integer> secretMessage) {
        this.receivingKingdom = receivingKingdom;
        this.secretMessage = secretMessage;
    }

    public String getReceivingKingdom() {
        return receivingKingdom;
    }

    public List<Integer> getSecretMessage() {
        return secretMessage;
    }
}

class OutputObject {
    private final boolean allegiance;
    private final Kingdom receivingKingdom;

    OutputObject(Kingdom kingdom, boolean allegiance) {
        this.receivingKingdom = kingdom;
        this.allegiance = allegiance;
    }

    public Kingdom getReceivingKingdom() {
        return receivingKingdom;
    }

    public boolean isAllegiance() {
        return allegiance;
    }
}
