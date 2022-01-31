package com.goldencrown.services;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;

public class BaseKingdomService {

    private DecipherMessageService decipherMessageService;

    public BaseKingdomService(DecipherMessageService decipherMessageService) {
        this.decipherMessageService = decipherMessageService;
    }

    public boolean respondToSecretMessage(SecretMessage secretMessage) {
        BaseKingdom receiverKingdom = secretMessage.getReceiverKingdom();
        String message = secretMessage.getMessage();

        boolean response = decipherMessageService.decipherSecretMessage(receiverKingdom, message);
        return response;
    }

}
