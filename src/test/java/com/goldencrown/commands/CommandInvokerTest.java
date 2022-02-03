package com.goldencrown.commands;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.Kingdoms.Space;
import com.goldencrown.exceptions.NoSuchCommandException;
import com.goldencrown.repositories.IRulerKingdomRepository;
import com.goldencrown.repositories.RulerKingdomRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandInvokerTest {
    private final CommandInvoker commandInvoker = new CommandInvoker();

    @Test
    @DisplayName("registerCommand should register given command")
    public void registerCommand_shouldRegisterGivenCommand() {
        // pre checks
        Assertions.assertNull(commandInvoker.getCommand("SEND_MESSAGE"));

        ICommand sendMessageCommand = new SendMessageCommand(null, null, null);
        commandInvoker.registerCommand("SEND_MESSAGE", sendMessageCommand);

        Assertions.assertEquals(sendMessageCommand, commandInvoker.getCommand("SEND_MESSAGE"));
    }

    @Test
    @DisplayName("executeCommand should thrown NoSuchCommandException if invalid command passed")
    public void executeCommand_shouldThrowNoSuchCommandException_ifInvalidCommandPassed() {
        Assertions.assertThrows(NoSuchCommandException.class,
                () -> commandInvoker.executeCommand("INVALID COMMAND", new ArrayList<>()));
    }

    @Test
    @DisplayName("executeCommand should execute command if valid command passed")
    public void executeCommand_shouldExecuteCommand_ifValidCommandPassed() throws NoSuchCommandException {
        // setup
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        IRulerKingdom rulerKingdom = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        IRulerKingdomRepository rulerKingdomRepository = new RulerKingdomRepository();
        rulerKingdomRepository.registerKingdom(rulerKingdom);

        ICommand displayOutputCommand = new DisplayOutputCommand(rulerKingdomRepository);
        commandInvoker.registerCommand("DISPLAY_OUTPUT", displayOutputCommand);

        // actual test
        commandInvoker.executeCommand("DISPLAY_OUTPUT", new ArrayList<>(Arrays.asList("SPACE")));
        Assertions.assertEquals("NONE", outputStreamCaptor.toString().trim());

        System.setOut(standardOut);
    }
}
