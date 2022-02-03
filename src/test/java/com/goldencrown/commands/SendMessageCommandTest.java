package com.goldencrown.commands;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Space;
import com.goldencrown.repositories.BaseKingdomRepository;
import com.goldencrown.repositories.IBaseKindgomRepository;
import com.goldencrown.services.BaseKingdomService;
import com.goldencrown.services.DecipherMessageService;
import com.goldencrown.services.IBaseKingdomService;
import com.goldencrown.services.IDecipherMessageService;
import com.goldencrown.services.IRulerKingdomService;
import com.goldencrown.services.RulerKingdomService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SendMessageCommandTest {
    private IRulerKingdom rulerKingdom;
    private IBaseKindgomRepository baseKingdomRepository;

    private SendMessageCommand sendMessageCommand;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
        rulerKingdom = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        IRulerKingdomService rulerKingdomService = new RulerKingdomService(rulerKingdom);
        baseKingdomRepository = new BaseKingdomRepository();
        IDecipherMessageService decipherMessageService = new DecipherMessageService();
        IBaseKingdomService baseKingdomService = new BaseKingdomService(decipherMessageService);
        sendMessageCommand = new SendMessageCommand(rulerKingdomService, baseKingdomService, baseKingdomRepository);
    }

    @Test
    @DisplayName("execute method should process tokens and add kingdom to ruler's allies if message is valid")
    public void execute_shouldAddKingdomToRulersAllies_ifMessageValid() {
        BaseKingdom air = new Air("AIR", "OWL", new LinkedHashSet<>());
        baseKingdomRepository.registerKingdom(air);
        
        // pre checks
        Assertions.assertTrue(rulerKingdom.getAllies().isEmpty());

        // actual test
        sendMessageCommand.execute(new ArrayList<>(Arrays.asList("AIR", "ROZO")));
        Assertions.assertTrue(rulerKingdom.getAllies().contains(air));
    }

    @Test
    @DisplayName("execute method should process tokens and not add kingdom to ruler's allies if message is invalid")
    public void execute_shouldNotAddKingdomToRulersAllies_ifMessageInvalid() {
        BaseKingdom air = new Air("AIR", "OWL", new LinkedHashSet<>());
        baseKingdomRepository.registerKingdom(air);
        
        // pre checks
        Assertions.assertTrue(rulerKingdom.getAllies().isEmpty());

        // actual test
        sendMessageCommand.execute(new ArrayList<>(Arrays.asList("AIR", "INVALID")));
        Assertions.assertFalse(rulerKingdom.getAllies().contains(air));
    }

    @Test
    @DisplayName("execute method should process tokens and print error to console if invalid kingdom name passed")
    public void execute_shouldPrintErrorToConsole_ifInvalidKingdomPassed() {
        String expected = "Invalid kingdom passed";
        
        sendMessageCommand.execute(new ArrayList<>(Arrays.asList("INVALID", "ROZO")));
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute method should process tokens and print error to console if null messae passed")
    public void execute_shouldPrintErrorToConsole_ifInvalidNullMessagePassed() {
        BaseKingdom air = new Air("AIR", "OWL", new LinkedHashSet<>());
        baseKingdomRepository.registerKingdom(air);
        String expected = "Invalid message passed";
        
        sendMessageCommand.execute(new ArrayList<>(Arrays.asList("AIR", null)));
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
