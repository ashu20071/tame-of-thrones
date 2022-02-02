package com.goldencrown.entities;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Fire;
import com.goldencrown.entities.Kingdoms.Land;
import com.goldencrown.entities.Kingdoms.Space;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RulerKingdomTest {

    private String kingdomName;
    private String emblem;
    private Set<BaseKingdom> allies;
    private String kingName;

    private IRulerKingdom rulerKingdom;
    private BaseKingdom air;
    private BaseKingdom land;
    private BaseKingdom fire;

    @BeforeEach
    public void setup() {
        kingdomName = "SPACE";
        emblem = "GORILLA";
        allies = new LinkedHashSet<>();
        kingName = "SHAN";

        rulerKingdom = new Space(kingdomName, emblem, allies, kingName);
        air = new Air("AIR", "OWL", new LinkedHashSet<>());
        land = new Land("LAND", "PANDA", new LinkedHashSet<>());
        fire = new Fire("FIRE", "DRAGON", new LinkedHashSet<>());
    }

    @Test
    @DisplayName("addAlly method should add base kingdom to ruler's ally list and add itself to all allies")
    public void addAlly_shouldAddBaseKingdomToAlliesList() {
        rulerKingdom.addAlly(air);
        
        Assertions.assertTrue(rulerKingdom.getAllies().contains(air));
        Assertions.assertTrue(air.getAllies().contains((BaseKingdom) rulerKingdom));
    }

    @Test
    @DisplayName("removeAlly method should remove base kingdom from ruler's ally list and remove itself from that kingdom's allies")
    public void removeAlly_shouldRemoveBaseKingdomFromAllies() {
        Set<BaseKingdom> allies = new LinkedHashSet<>(Arrays.asList(new BaseKingdom[] { air }));
        rulerKingdom = new Space(kingdomName, emblem, allies, kingName);

        rulerKingdom.removeAlly(air);
        Assertions.assertFalse(rulerKingdom.getAllies().contains(air));
        Assertions.assertFalse(air.getAllies().contains((BaseKingdom) rulerKingdom));
    }

    @Test
    @DisplayName("addAlly method should set kingdom as ruler if allies are 3 or more")
    public void addAlly_shouldSetRulerStatusAsRuler() {
        Set<BaseKingdom> allies = new LinkedHashSet<>(Arrays.asList(new BaseKingdom[] { air, land }));
        rulerKingdom = new Space(kingdomName, emblem, allies, kingName);

        // pre checks
        Assertions.assertEquals(2, rulerKingdom.getAllies().size());
        Assertions.assertEquals(RulerStatus.NOT_RULER, rulerKingdom.getRulerStatus());

        // actual test
        rulerKingdom.addAlly(fire);
        Assertions.assertEquals(RulerStatus.IS_RULER, rulerKingdom.getRulerStatus());
    }

    @Test
    @DisplayName("removeAlly mothod should set kingdom as not ruler if allies are less than 3")
    public void removeAlly_shouldSetRulerStatusAsNotRuler() {
        Set<BaseKingdom> allies = new LinkedHashSet<>(Arrays.asList(new BaseKingdom[] { air, land, fire }));
        rulerKingdom = new Space(kingdomName, emblem, allies, kingName);

        // pre checks
        Assertions.assertEquals(3, rulerKingdom.getAllies().size());
        Assertions.assertEquals(RulerStatus.IS_RULER, rulerKingdom.getRulerStatus());

        // actual test
        rulerKingdom.removeAlly(air);
        Assertions.assertEquals(RulerStatus.NOT_RULER, rulerKingdom.getRulerStatus());
    }

    @Test
    @DisplayName("sendSecretMessage method should should create new secreteMessage object with itself as sender kingdom")
    public void sendSecretMessage_shouldCreateNewSecretMessageObject() {
        String message = "Test message";
        SecretMessage secretMessage = new SecretMessage(rulerKingdom, air, message);

        Assertions.assertEquals(secretMessage, rulerKingdom.sendSecretMessage(air, message));
    }
}
