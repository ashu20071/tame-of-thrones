package com.goldencrown.config;

import java.util.LinkedHashSet;

import com.goldencrown.commands.*;
import com.goldencrown.entities.*;
import com.goldencrown.entities.Kingdoms.*;
import com.goldencrown.repositories.*;
import com.goldencrown.services.*;

public class ApplicationConfig {

    private final IBaseKindgomRepository baseKingdomRepository;
    private final IRulerKingdomRepository rulerKingdomRepository;

    private final ICommand sendMessageCommand;
    private final ICommand displayOutputCommand;

    private final CommandInvoker commandInvoker = new CommandInvoker();

    private final String RULER_KINGDOM = "SPACE";

    public ApplicationConfig() {
        baseKingdomRepository = new BaseKingdomRepository();
        rulerKingdomRepository = new RulerKingdomRepository();

        IDecipherMessageService decipherMessageService = new DecipherMessageService();
        IBaseKingdomService baseKingdomService = new BaseKingdomService(decipherMessageService);

        registerKingdoms();

        IRulerKingdom rulerKingdom = rulerKingdomRepository.getKingdomByName(RULER_KINGDOM);
        IRulerKingdomService rulerKingdomService = new RulerKingdomService(rulerKingdom);

        sendMessageCommand = new SendMessageCommand(rulerKingdomService, baseKingdomService, baseKingdomRepository);
        displayOutputCommand = new DisplayOutputCommand(rulerKingdomRepository);
    }

    private void registerKingdoms() {
        baseKingdomRepository.registerKingdom(new Space("SPACE", "GORILLA", new LinkedHashSet<>()));
        baseKingdomRepository.registerKingdom(new Air("AIR", "OWL", new LinkedHashSet<>()));
        baseKingdomRepository.registerKingdom(new Land("LAND", "PANDA", new LinkedHashSet<>()));
        baseKingdomRepository.registerKingdom(new Water("WATER", "OCTOPUS", new LinkedHashSet<>()));
        baseKingdomRepository.registerKingdom(new Ice("ICE", "MAMMOTH", new LinkedHashSet<>()));
        baseKingdomRepository.registerKingdom(new Fire("FIRE", "DRAGON", new LinkedHashSet<>()));

        rulerKingdomRepository
                .registerKingdom(new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN"));
    }

    public CommandInvoker getCommandInvoker() {
        commandInvoker.registerCommand("SEND_MESSAGE", sendMessageCommand);
        commandInvoker.registerCommand("DISPLAY_OUTPUT", displayOutputCommand);
        return commandInvoker;
    }

    public String getRulerKingdom() {
        return RULER_KINGDOM;
    }
}
