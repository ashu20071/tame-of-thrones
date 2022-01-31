package com.goldencrown.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.goldencrown.entities.Kingdoms.Space;

import org.junit.Test;

public class KingdomTest {

    @Test
    public void kingdomTest() {
        BaseKingdom kingdom = new Space();
        Space space = new Space();
        assertEquals(kingdom, space);
    }
}
