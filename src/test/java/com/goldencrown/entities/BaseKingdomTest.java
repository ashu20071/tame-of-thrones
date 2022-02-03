package com.goldencrown.entities;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Fire;
import com.goldencrown.entities.Kingdoms.Ice;
import com.goldencrown.entities.Kingdoms.Land;
import com.goldencrown.entities.Kingdoms.Space;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BaseKingdomTest {

    private BaseKingdom air;
    private BaseKingdom space;
    private BaseKingdom fire;
    private BaseKingdom land;
    private BaseKingdom ice;

    @BeforeEach
    public void setup() {
        air = new Air("AIR", "OWL", new LinkedHashSet<>());
        space = new Space("SPACE", "GORILLA", new LinkedHashSet<>());
        fire = new Fire("FIRE", "DRAGON", new LinkedHashSet<>());
        land = new Land("LAND", "PANDA", new LinkedHashSet<>());
        ice = new Ice("ICE", "MAMMOTH", new LinkedHashSet<>());
    }

    @Test
    @DisplayName("creating new base kingdom with passed non-empty ally list should not add duplicate allies")
    public void createBaseKingdom_shouldNotAddDuplicateAllies() {
        Set<BaseKingdom> allies = new LinkedHashSet<>(
                Arrays.asList(air, air, air));
        BaseKingdom baseKingdom = new Fire("FIRE", "DRAGON", allies);

        // 3 kingdoms added but only 1 unique
        Assertions.assertEquals(1, baseKingdom.getAllies().size());
    }

    @Test
    @DisplayName("creating new base kingdom with passed non-empty ally list should add itself to all allies")
    public void createBaseKingdom_shouldAddItselfToAllies() {
        Set<BaseKingdom> allies = new LinkedHashSet<>(Arrays.asList(ice, space));
        BaseKingdom baseKingdom = new Fire("FIRE", "DRAGON", allies);

        // passed kingdoms at creation should be allied to each other
        Assertions.assertTrue(ice.getAllies().contains(baseKingdom));
        Assertions.assertTrue(space.getAllies().contains(baseKingdom));

        // allies of allies should not be allies
        Assertions.assertFalse(ice.getAllies().contains(space));
    }

    @Test
    @DisplayName("creating new base kingdom with passed non-empty ally list should validate allies, removing itself if present")
    public void createBaseKingdom_shouldValidateAllies() {
        Set<BaseKingdom> allies = new LinkedHashSet<>(Arrays.asList(land, space));
        BaseKingdom baseKingdom = new Space("SPACE", "GORILLA", allies);

        Assertions.assertFalse(baseKingdom.getAllies().contains(space));
    }

    @Test
    @DisplayName("addAlly method should add that kingdom to all its allies as well")
    public void addAlly_shouldAddItselfToAllies() {
        air.addAlly(space);
        air.addAlly(fire);

        // passed kingdoms should be allied to each other
        Assertions.assertTrue(space.getAllies().contains(air));
        Assertions.assertTrue(fire.getAllies().contains(air));

        // only passed kingdoms should be added
        Assertions.assertFalse(space.getAllies().contains(fire));
    }

    @Test
    @DisplayName("removeAlly method should remove given ally and also remove itself from that kingdom's allies")
    public void removeAlly_shouldRemoveItselfFromAllies() {
        Set<BaseKingdom> allies = new LinkedHashSet<>(Arrays.asList(air, space));

        BaseKingdom baseKingdom = new Fire("FIRE", "DRAGON", allies);
        baseKingdom.removeAlly(air);

        // passed allies should be removed
        Assertions.assertFalse(baseKingdom.getAllies().contains(air));
        Assertions.assertFalse(air.getAllies().contains(baseKingdom));

        // other allies should remain
        Assertions.assertTrue(baseKingdom.getAllies().contains(space));
        Assertions.assertTrue(space.getAllies().contains(baseKingdom));
    }

}
