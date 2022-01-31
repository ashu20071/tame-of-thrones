package com.goldencrown.entities;

import java.util.List;

import com.goldencrown.dto.SecretMessage;

public interface IRulerKingdom {

    final Integer REQUIRED_ALLIES = 3;

    List<BaseKingdom> getAllies();

    void addAlly(BaseKingdom kingdom);

    RulerStatus getRulerStatus();

    SecretMessage sendSecretMessage(BaseKingdom receiverKingdom, String secretMessage);

}
