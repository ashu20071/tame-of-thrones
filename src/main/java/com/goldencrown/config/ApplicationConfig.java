package com.goldencrown.config;

import java.util.LinkedHashSet;

import com.goldencrown.commands.*;
import com.goldencrown.entities.*;
import com.goldencrown.entities.Kingdoms.*;
import com.goldencrown.repositories.*;
import com.goldencrown.services.*;

public class ApplicationConfig {

    private final IBaseKindgomRepository baseKindgomRepository;
    private final IRulerKingdomRepository rulerKingdomRepository;

    private final IDecipherMessageService decipherMessageService;
    private final IBaseKingdomService baseKingdomService;
    private final IRulerKingdomService rulerKingdomService;

    private final ICommand sendMessageCommand;
    private final ICommand displayOutputCommand;

    private final CommandInvoker commandInvoker = new CommandInvoker();

    private final String RULER_KINGDOM = "SPACE";

    public ApplicationConfig() {
        baseKindgomRepository = new BaseKingdomRepository();
        rulerKingdomRepository = new RulerKingdomRepository();

        decipherMessageService = new DecipherMessageService();
        baseKingdomService = new BaseKingdomService(decipherMessageService);

        registerKingdoms();

        IRulerKingdom rulerKingdom = rulerKingdomRepository.getKingdomByName(RULER_KINGDOM);
        rulerKingdomService = new RulerKingdomService(rulerKingdom);

        sendMessageCommand = new SendMessageCommand(rulerKingdomService, baseKingdomService, baseKindgomRepository);
        displayOutputCommand = new DisplayOutputCommand(rulerKingdomRepository);
    }

    private void registerKingdoms() {
        baseKindgomRepository.registerKingdom(new Space("SPACE", "GORILLA", new LinkedHashSet<>()));
        baseKindgomRepository.registerKingdom(new Air("AIR", "OWL", new LinkedHashSet<>()));
        baseKindgomRepository.registerKingdom(new Land("LAND", "PANDA", new LinkedHashSet<>()));
        baseKindgomRepository.registerKingdom(new Water("WATER", "OCTOPUS", new LinkedHashSet<>()));
        baseKindgomRepository.registerKingdom(new Ice("ICE", "MAMMOTH", new LinkedHashSet<>()));
        baseKindgomRepository.registerKingdom(new Fire("FIRE", "DRAGON", new LinkedHashSet<>()));

        rulerKingdomRepository
                .registerKingdom(new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN"));
    }

    public CommandInvoker getCommandInvoker() {
        commandInvoker.registerCommand("SEND_MESSAGE", sendMessageCommand);
        commandInvoker.registerCommand("DISPLAY_OUTPUT", displayOutputCommand);
        return commandInvoker;
    }
}
