package com.goldencrown.services;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;

public class RulerKingdomService {
    private IRulerKingdom rulerKingdom;

    public RulerKingdomService(IRulerKingdom rulerKingdom) {
        this.rulerKingdom = rulerKingdom;
    }

    public SecretMessage sendSecretMessage(BaseKingdom receiverKingdom, String secretMessage) {
        if (receiverKingdom.equals(rulerKingdom) || secretMessage == null)
            throw new IllegalArgumentException("Invalid operation");
        return rulerKingdom.sendSecretMessage(receiverKingdom, secretMessage);
    }

    public void receiveResponse(SecretMessage secretMessage, boolean response) {
        BaseKingdom receiverKingdom = secretMessage.getReceiverKingdom();
        if (response)
            rulerKingdom.addAlly(receiverKingdom);
    }

}
