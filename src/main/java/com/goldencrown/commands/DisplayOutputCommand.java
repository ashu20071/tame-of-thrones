package com.goldencrown.commands;

import java.util.List;

import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.RulerStatus;
import com.goldencrown.repositories.IRulerKingdomRepository;

public class DisplayOutputCommand implements ICommand {
    private final IRulerKingdomRepository rulerKingdomRepository;

    public DisplayOutputCommand(IRulerKingdomRepository rulerKingdomRepository) {
        this.rulerKingdomRepository = rulerKingdomRepository;
    }

    @Override
    public void execute(List<String> tokens) {
        // Retrieve parameters from tokens
        String kingdomName = tokens.get(0);
        IRulerKingdom rulerKingdom = rulerKingdomRepository.getKingdomByName(kingdomName);
        RulerStatus rulerStatus = rulerKingdom.getRulerStatus();

        // If kingdom has become ruler; print kingdom and their allies' name
        if (rulerStatus.equals(RulerStatus.IS_RULER))
            System.out.println(declareVictory(rulerKingdom));
        else
            System.out.println("NONE");

    }

    private String declareVictory(IRulerKingdom rulerKingdom) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rulerKingdom.getKingdomName()).append(" ");

        rulerKingdom.getAllies().forEach(ally -> {
            stringBuilder.append(ally.getKingdomName()).append(" ");
        });

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}
