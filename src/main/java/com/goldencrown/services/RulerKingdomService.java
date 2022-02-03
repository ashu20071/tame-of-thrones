package com.goldencrown.services;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.exceptions.InvalidKingdomException;
import com.goldencrown.exceptions.InvalidMessageException;

public class RulerKingdomService implements IRulerKingdomService {
    private final IRulerKingdom rulerKingdom;

    public RulerKingdomService(IRulerKingdom rulerKingdom) {
        this.rulerKingdom = rulerKingdom;
    }

    /**
     * Build secret message for passed params to send to kingdoms to gain their
     * allegiance
     * Throws exception if invalid kingdom or null message passed
     */
    @Override
    public SecretMessage sendSecretMessage(BaseKingdom receiverKingdom, String secretMessage)
            throws InvalidMessageException, InvalidKingdomException {
        if (secretMessage == null)
            throw new InvalidMessageException();
        if (receiverKingdom == null || receiverKingdom.equals((BaseKingdom) rulerKingdom))
            throw new InvalidKingdomException();
        return rulerKingdom.sendSecretMessage(receiverKingdom, secretMessage);
    }

    /**
     * Receive and process response sent back by kingdom in response to secret
     * message
     * If allegiance acquired; add kingdom to ally list
     * 
     * @param secretMessage sent by this ruler kingdom
     * @param response      sent back by receiver kingdom
     */
    @Override
    public void receiveResponse(SecretMessage secretMessage, boolean response) throws InvalidKingdomException {
        if (response)
            addAlly(secretMessage.getReceiverKingdom());
    }

    @Override
    public void addAlly(BaseKingdom kingdom) throws InvalidKingdomException {
        if (kingdom.equals((BaseKingdom) rulerKingdom))
            throw new InvalidKingdomException();
        rulerKingdom.addAlly(kingdom);
    }

    @Override
    public void removeAlly(BaseKingdom kingdom) throws InvalidKingdomException {
        if (!rulerKingdom.getAllies().contains(kingdom))
            throw new InvalidKingdomException();
        rulerKingdom.removeAlly(kingdom);
    }

}
