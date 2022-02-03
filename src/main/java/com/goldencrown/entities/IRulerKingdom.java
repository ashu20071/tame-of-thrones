package com.goldencrown.entities;

import java.util.Set;

import com.goldencrown.dto.SecretMessage;

public interface IRulerKingdom {

    Integer REQUIRED_ALLIES = 3;

    String getKingdomName();

    String getKingName();

    Set<BaseKingdom> getAllies();

    void addAlly(BaseKingdom kingdom);

    void removeAlly(BaseKingdom kingdom);

    RulerStatus getRulerStatus();

    void setRulerStatus(RulerStatus rulerStatus);

    SecretMessage sendSecretMessage(BaseKingdom receiverKingdom, String secretMessage);

}
