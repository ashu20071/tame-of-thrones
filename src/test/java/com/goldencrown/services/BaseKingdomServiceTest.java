package com.goldencrown.services;

import java.util.LinkedHashSet;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Space;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BaseKingdomServiceTest {
    private final IDecipherMessageService decipherMessageService = new DecipherMessageService();
    private final IBaseKingdomService baseKingdomService = new BaseKingdomService(decipherMessageService);

    @Test
    @DisplayName("respondToSecretMessage method should respond true if valid secret message passed")
    public void respondToSecretMessage_shouldRespondTrue_ifValidSecretMessagePassed() {
        IRulerKingdom rulerKingdom = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        BaseKingdom baseKingdom = new Air("AIR", "OWL", new LinkedHashSet<>());
        SecretMessage secretMessage = new SecretMessage(rulerKingdom, baseKingdom, "ROZO");

        Assertions.assertTrue(baseKingdomService.respondToSecretMessage(secretMessage));
    }

    @Test
    @DisplayName("respondToSecretMessage method should respond false if invalid secret message passed")
    public void respondToSecretMessage_shouldRespondFalse_ifInvalidSecretMessagePassed() {
        IRulerKingdom rulerKingdom = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        BaseKingdom baseKingdom = new Air("AIR", "OWL", new LinkedHashSet<>());
        SecretMessage secretMessage = new SecretMessage(rulerKingdom, baseKingdom, "OWL");

        Assertions.assertFalse(baseKingdomService.respondToSecretMessage(secretMessage));
    }
}
