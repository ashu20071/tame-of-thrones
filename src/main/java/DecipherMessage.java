import java.util.List;

/**
 * Class with method to decipher secret message sent to a kingdom.
 */

public class DecipherMessage {
    public boolean decipher(InputObject inputObject, Kingdom receivingKingdom) {
        List<Integer> secretMessage = inputObject.getSecretMessage();
        for (int counter = 0; counter < receivingKingdom.cipherKey; counter++) {
            int messageCode = receivingKingdom.symbol.charAt(counter) + receivingKingdom.cipherKey;
            // Checking for overflow in ascii
            messageCode = (messageCode > 'Z') ? messageCode - 'Z' + '@' : messageCode;
            // remove char if found to avoid duplicate matching
            if (secretMessage.contains(messageCode))
                secretMessage.remove((Integer) messageCode);
            else
                return false;
        }
        return true;
    }
}
