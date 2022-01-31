package com.goldencrown.entities.Kingdoms;

import java.util.ArrayList;
import java.util.List;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.RulerStatus;

public class Space extends BaseKingdom implements IRulerKingdom {

    private final String kingName;
    private RulerStatus rulerStatus;

    public Space() {
        this.kingName = "Shan";
        this.kingdomName = "SPACE";
        this.emblem = "Gorilla";
        this.allies = new ArrayList<>();
        this.rulerStatus = RulerStatus.NOT_RULER;
    }

    public Space(String kingdomName, String emblem, String kingName, List<BaseKingdom> allies,
            RulerStatus rulerStatus) {
        this.kingdomName = kingdomName;
        this.emblem = emblem;
        this.kingName = kingName;
        this.allies = allies;
        this.rulerStatus = rulerStatus;
    }

    public String getKingName() {
        return kingName;
    }

    @Override
    public void addAlly(BaseKingdom kingdom) {
        allies.add(kingdom);
        if (allies.size() >= REQUIRED_ALLIES)
            rulerStatus = RulerStatus.IS_RULER;
    }

    @Override
    public SecretMessage sendSecretMessage(BaseKingdom receiverKingdom, String secretMessage) {
        return new SecretMessage(this, receiverKingdom, secretMessage);
    }

    @Override
    public RulerStatus getRulerStatus() {
        return rulerStatus;
    }

}
