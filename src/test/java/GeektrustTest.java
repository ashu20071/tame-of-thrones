import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for validating core methods.
 */

public class GeektrustTest {
    Controller controller;
    KingdomLookup kingdomLookup;
    KingdomService kingdomService;
    InputObject inputObject;
    DecipherMessage decipherMessage;
    Kingdom sendingKingdom;

    @BeforeEach
    void setUp() {
        controller = new Controller("SPACE");
        kingdomLookup = new KingdomLookup();
        sendingKingdom = (Kingdom) kingdomLookup.getKingdom("SPACE");
        inputObject = controller.validateMessage("AIR ROZO");
        decipherMessage = new DecipherMessage();
    }

    @AfterEach
    void tearDown() {
        controller = null;
        kingdomLookup = null;
        sendingKingdom = null;
        inputObject = null;
        decipherMessage = null;
    }

    @Test
    void validateMessage() {
        String message = "ROZO";
        List<Integer> secretMessage = new ArrayList<>();
        // Store ascii values of char in secret message
        for (int count = 0; count < message.length(); count++)
            secretMessage.add((int) message.charAt(count));
        assertEquals("AIR", inputObject.getReceivingKingdom());
        assertEquals(secretMessage, inputObject.getSecretMessage());
        assertThrows(IllegalArgumentException.class, () -> {
            controller.validateMessage("AIR");
        });
    }

    @Test
    void processMessage() {
        kingdomService = kingdomLookup.getKingdom("AIR");
        OutputObject outputObject = kingdomService.decipherSecretMessage(inputObject);
        assertTrue(outputObject.isAllegiance());
    }

    @Test
    void buildOutput() {
        Kingdom kingdomSpace = controller.sendingKingdom;
        kingdomSpace.addAlly(new Air());
        kingdomSpace.addAlly(new Water());
        kingdomSpace.addAlly(new Land());
        assertEquals("SPACE AIR WATER LAND",
                controller.buildOutput(new OutputObject(new Space(), true)));
    }

    @Test
    void decipher() {
        assertTrue(decipherMessage.decipher(inputObject, new Air()));
        assertFalse(decipherMessage.decipher(inputObject, new Ice()));
    }
}
