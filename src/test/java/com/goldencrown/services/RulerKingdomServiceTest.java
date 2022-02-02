package com.goldencrown.services;

import java.util.LinkedHashSet;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Space;
import com.goldencrown.exceptions.InvalidKingdomException;
import com.goldencrown.exceptions.InvalidMessageException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RulerKingdomServiceTest {

    private IRulerKingdom rulerKingdom;
    private IRulerKingdomService rulerKingdomService;

    @BeforeEach
    public void setup() {
        rulerKingdom = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        rulerKingdomService = new RulerKingdomService(rulerKingdom);
    }

    @Test
    @DisplayName("sendSecretMessage method should throw InvalidMessageException if null message is passed")
    public void sendSecretMessage_shouldThrowInvalidMessageException_IfMessageIsNull() {
        Assertions.assertThrows(InvalidMessageException.class,
                () -> rulerKingdomService.sendSecretMessage(new Air("AIR", "OWL", new LinkedHashSet<>()),
                        null));
    }

    @Test
    @DisplayName("sendSecretMessage method should throw InvalidKingdomException if ruler kingdom is passed as receiver kingdom or null kingdom passed")
    public void sendSecretMessage_shouldThrowInvalidKingdomException() {

        Assertions.assertThrows(InvalidKingdomException.class,
                () -> rulerKingdomService.sendSecretMessage((BaseKingdom) rulerKingdom, "Test message"));

        Assertions.assertThrows(InvalidKingdomException.class,
                () -> rulerKingdomService.sendSecretMessage(new Space("SPACE", "GORILLA", new LinkedHashSet<>()),
                        "Test message"));

        Assertions.assertThrows(InvalidKingdomException.class,
                () -> rulerKingdomService.sendSecretMessage(null, "Test message"));
    }

    @Test
    @DisplayName("sendSecretMessage method should create secret message if valid parameters passed")
    public void sendSecretMessage_shouldCreateNewSecretMessageObject()
            throws InvalidMessageException, InvalidKingdomException {

        BaseKingdom baseKingdom = new Air("AIR", "OWL", new LinkedHashSet<>());
        SecretMessage expected = new SecretMessage(rulerKingdom, baseKingdom, "secretMessage");

        SecretMessage actual = rulerKingdomService.sendSecretMessage(baseKingdom, "secretMessage");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("receiveResponse method should add base kingdom to ruler's allies if response if true")
    public void receiveResponse_shouldAddKingdomToAllies_IfResponseIsTrue() throws InvalidKingdomException {
        BaseKingdom baseKingdom = new Air("AIR", "OWL", new LinkedHashSet<>());
        SecretMessage secretMessage = new SecretMessage(rulerKingdom, baseKingdom, "secretMessage");
        // pre check
        Assertions.assertFalse(rulerKingdom.getAllies().contains(baseKingdom));

        // actual test
        rulerKingdomService.receiveResponse(secretMessage, true);
        Assertions.assertTrue(rulerKingdom.getAllies().contains(baseKingdom));

    }

    @Test
    @DisplayName("receiveResponse method should not add base kingdom to ruler's allies if response if false")
    public void receiveResponse_shouldNotAddKingdomToAllies_IfResponseIsFalse() throws InvalidKingdomException {
        BaseKingdom baseKingdom = new Air("AIR", "OWL", new LinkedHashSet<>());
        SecretMessage secretMessage = new SecretMessage(rulerKingdom, baseKingdom, "secretMessage");
        // pre check
        Assertions.assertFalse(rulerKingdom.getAllies().contains(baseKingdom));

        // actual test
        rulerKingdomService.receiveResponse(secretMessage, false);
        Assertions.assertFalse(rulerKingdom.getAllies().contains(baseKingdom));
    }

    @Test
    @DisplayName("addAlly method should throw InvalidKingdomException if same ruler kingdom is passed")
    public void addAlly_shouldThrowInvalidKingdomException_IfSameKingdomPassed() {
        Assertions.assertThrows(InvalidKingdomException.class,
                () -> rulerKingdomService.addAlly((BaseKingdom) rulerKingdom));
    }

    @Test
    @DisplayName("removeAlly method should throw InvalidKingdomException if passed kingdom is not present in ally list")
    public void removeAlly_shouldThrowInvalidKingdomException_IfKingdomNotPresent() throws InvalidKingdomException {
        BaseKingdom baseKingdom = new Air("AIR", "OWL", new LinkedHashSet<>());
        // test without adding
        Assertions.assertThrows(InvalidKingdomException.class,
                () -> rulerKingdomService.removeAlly(baseKingdom));

        rulerKingdomService.addAlly(baseKingdom);
        rulerKingdomService.removeAlly(baseKingdom);

        // test after adding and deleting
        Assertions.assertThrows(InvalidKingdomException.class,
                () -> rulerKingdomService.removeAlly(baseKingdom));
    }
}
