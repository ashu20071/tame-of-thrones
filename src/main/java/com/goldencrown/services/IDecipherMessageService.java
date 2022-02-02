package com.goldencrown.services;

import com.goldencrown.entities.BaseKingdom;

public interface IDecipherMessageService {
    boolean decipherSecretMessage(BaseKingdom receiverKingdom, String message);
}
