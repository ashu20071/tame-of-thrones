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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((receiverKingdom == null) ? 0 : receiverKingdom.hashCode());
        result = prime * result + ((senderKingdom == null) ? 0 : senderKingdom.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SecretMessage other = (SecretMessage) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (receiverKingdom == null) {
            if (other.receiverKingdom != null)
                return false;
        } else if (!receiverKingdom.equals(other.receiverKingdom))
            return false;
        if (senderKingdom == null) {
            if (other.senderKingdom != null)
                return false;
        } else if (!senderKingdom.equals(other.senderKingdom))
            return false;
        return true;
    }

}
