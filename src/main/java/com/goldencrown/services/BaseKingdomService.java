package com.goldencrown.services;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;

public class BaseKingdomService implements IBaseKingdomService {

    private final IDecipherMessageService decipherMessageService;

    public BaseKingdomService(IDecipherMessageService decipherMessageService) {
        this.decipherMessageService = decipherMessageService;
    }

    /**
     * @param secretMessage sent by aspiring ruler kingdom in attempt to gain
     *                      receiver's allegiance
     * @return true only if message contains all ciphered letters of receiver's
     *         emblem
     */
    @Override
    public boolean respondToSecretMessage(SecretMessage secretMessage) {
        BaseKingdom receiverKingdom = secretMessage.getReceiverKingdom();
        String message = secretMessage.getMessage();

        // Use decipher message service to decipher given message
        return decipherMessageService.decipherSecretMessage(receiverKingdom, message);
    }

}
