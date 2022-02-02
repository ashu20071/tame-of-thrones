package com.goldencrown.services;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.exceptions.InvalidKingdomException;
import com.goldencrown.exceptions.InvalidMessageException;

public interface IRulerKingdomService {
    SecretMessage sendSecretMessage(BaseKingdom receiverKingdom, String message)
            throws InvalidMessageException, InvalidKingdomException;

    void receiveResponse(SecretMessage secretMessage, boolean response) throws InvalidKingdomException;

    void addAlly(BaseKingdom kingdom) throws InvalidKingdomException;

    void removeAlly(BaseKingdom kingdom) throws InvalidKingdomException;

}
