package com.goldencrown.services;

import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Space;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class RulerKingdomServiceTest {

    @Test
    public void test() {
        RulerKingdomService rulerKingdomService = new RulerKingdomService(new Space());
        Assertions.assertThrows(IllegalArgumentException.class, () -> rulerKingdomService.sendSecretMessage(new Air(),
                null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> rulerKingdomService.sendSecretMessage(new Space(),
                "secretMessage"));
    }
}
