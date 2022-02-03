package com.goldencrown.commands;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Land;
import com.goldencrown.entities.Kingdoms.Space;
import com.goldencrown.repositories.IRulerKingdomRepository;
import com.goldencrown.repositories.RulerKingdomRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DisplayOutputCommandTest {
    private IRulerKingdom rulerKingdom;
    private IRulerKingdomRepository rulerKingdomRepository;

    private DisplayOutputCommand displayOutputCommand;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
        rulerKingdomRepository = new RulerKingdomRepository();
        displayOutputCommand = new DisplayOutputCommand(rulerKingdomRepository);
    }

    @Test
    @DisplayName("execute command should print ruler kingdom and its allies if ruler has 3 or more allies")
    public void execute_shouldPrintRulerAndItsAllies_ifRulerHasThreeOrMoreAllies() {
        BaseKingdom air = new Air("AIR", "OWL", new LinkedHashSet<>());
        BaseKingdom land = new Land("LAND", "PANDA", new LinkedHashSet<>());
        BaseKingdom ice = new Air("ICE", "MAMMOTH", new LinkedHashSet<>());
        Set<BaseKingdom> allies = new LinkedHashSet<>(Arrays.asList(air, land, ice));

        rulerKingdom = new Space("SPACE", "GORILLA", allies, "SHAN");
        rulerKingdomRepository.registerKingdom(rulerKingdom);

        String expected = "SPACE AIR LAND ICE";
        displayOutputCommand.execute(new ArrayList<>(Arrays.asList("SPACE")));
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute command should print NONE if ruler has less than 3 allies")
    public void execute_shouldPrintNONE_ifRulerHasLessThanThreeAllies() {
        BaseKingdom air = new Air("AIR", "OWL", new LinkedHashSet<>());
        BaseKingdom land = new Land("LAND", "PANDA", new LinkedHashSet<>());
        Set<BaseKingdom> allies = new LinkedHashSet<>(Arrays.asList(air, land));

        String expected = "NONE";

        rulerKingdom = new Space("SPACE", "GORILLA", allies, "SHAN");
        rulerKingdomRepository.registerKingdom(rulerKingdom);

        displayOutputCommand.execute(new ArrayList<>(Arrays.asList("SPACE")));
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute should throw null pointer exception if invalid ruler kingdom passed")
    public void execute_shouldThrowNullPointerException_ifInvalidRulerKingdomPassed() {
        Assertions.assertThrows(NullPointerException.class,
                () -> displayOutputCommand.execute(new ArrayList<>(Arrays.asList("INVALID"))));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
