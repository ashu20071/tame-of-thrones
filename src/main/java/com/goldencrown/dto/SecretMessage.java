package com.goldencrown.dto;

import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;

public class SecretMessage {

    private final IRulerKingdom senderKingdom;
    private final BaseKingdom receiverKingdom;
    private final String message;

    public SecretMessage(IRulerKingdom senderKingdom,
            BaseKingdom receiverKingdom, String secretMessage) {
        this.senderKingdom = senderKingdom;
        this.receiverKingdom = receiverKingdom;
        this.message = secretMessage;
    }    
    
    public IRulerKingdom getSenderKingdom() {
        return senderKingdom;
    }

    public BaseKingdom getReceiverKingdom() {
        return receiverKingdom;
    }

    public String getMessage() {
        return message;
    }

}
