/**
 * Interface to declare core process of deciphering secret message by generating cipher key specific to a kingdom.
 */

public interface KingdomService {
    int generateCipherKey();
    OutputObject decipherSecretMessage(InputObject inputObject);
}
