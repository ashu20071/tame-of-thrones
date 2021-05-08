import java.util.ArrayList;
import java.util.List;

/**
 * Controller class which defines various driver functions.
 */

public class Controller {
    KingdomLookup kingdomLookup;
    KingdomService kingdomService;
    Kingdom sendingKingdom;

    public Controller(String sendingKingdom) {
        kingdomLookup = new KingdomLookup();
        this.sendingKingdom = (Kingdom) kingdomLookup.getKingdom(sendingKingdom);
    }

    /**
     * Method to validate the given input message.
     * Build and return message object for service to decipher.
     * @param inputMessage Secret message given to kingdoms by King Shan of kingdom Space.
     * @return MessageObject containing kingdom name to which secret message is to be sent for deciphering.
     */

    public InputObject validateMessage(String inputMessage) {
        inputMessage = inputMessage.toUpperCase();

        String[] messageComponent = inputMessage.split(" ", 2);
        if (messageComponent.length < 2 || messageComponent[1].isBlank())
            throw new IllegalArgumentException("No message found");
        List<Integer> secretMessage = new ArrayList<>();
        // Store ascii values of char in secret message
        for (int count = 0; count < messageComponent[1].length(); count++)
            secretMessage.add((int) messageComponent[1].charAt(count));
        String kingdomName = messageComponent[0];
        return new InputObject(kingdomName, secretMessage);
    }

    public OutputObject processMessage(InputObject inputObject) {
        // Set service type based on given input
        kingdomService = kingdomLookup.getKingdom(inputObject.getReceivingKingdom());
        return kingdomService.decipherSecretMessage(inputObject);
    }

    public String buildOutput(OutputObject outputObject) {
        String outputMessage = sendingKingdom.name;
        if (outputObject.isAllegiance())
            sendingKingdom.addAlly(outputObject.getReceivingKingdom());
        if (sendingKingdom.allies.size() >= sendingKingdom.minAlliesRequired) {
            for (Kingdom kingdom : sendingKingdom.allies)
                outputMessage = outputMessage.concat(" " + kingdom.name);
        }
        else
            outputMessage = "NONE";
        return outputMessage;
    }
}