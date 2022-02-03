package com.goldencrown.commands;

import java.util.List;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.exceptions.InvalidKingdomException;
import com.goldencrown.exceptions.InvalidMessageException;
import com.goldencrown.repositories.IBaseKindgomRepository;
import com.goldencrown.services.IBaseKingdomService;
import com.goldencrown.services.IRulerKingdomService;

public class SendMessageCommand implements ICommand {

    private final IRulerKingdomService rulerKingdomService;
    private final IBaseKingdomService baseKingdomService;
    private final IBaseKindgomRepository baseKingdomRepository;

    public SendMessageCommand(IRulerKingdomService rulerKingdomService, IBaseKingdomService baseKingdomService,
            IBaseKindgomRepository baseKingdomRepository) {
        this.rulerKingdomService = rulerKingdomService;
        this.baseKingdomRepository = baseKingdomRepository;
        this.baseKingdomService = baseKingdomService;
    }

    @Override
    public void execute(List<String> tokens) {
        // Retrieve parameters from tokens
        String receiverKingdomName = tokens.get(0);
        String message = tokens.get(1);
        BaseKingdom receiverKingdom = baseKingdomRepository.getKingdomByName(receiverKingdomName);

        try {

            // Embed and send secret message to mentioned kingdom
            SecretMessage secretMessage = rulerKingdomService.sendSecretMessage(receiverKingdom, message);

            // Response received from kingdom after deciphering secret message
            boolean response = baseKingdomService.respondToSecretMessage(secretMessage);

            // Receive and process received response
            rulerKingdomService.receiveResponse(secretMessage, response);

        } catch (InvalidMessageException | InvalidKingdomException exception) {
            System.out.println(exception);
        }
    }

}
