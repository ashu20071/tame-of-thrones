package com.goldencrown.entities.Kingdoms;

import java.util.Set;

import com.goldencrown.dto.SecretMessage;
import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.RulerStatus;

/**
 * Space kingdom exhibits properties of both Base kingdom and Ruler kingdom in
 * its attempt to conquer Southeros
 */
public class Space extends BaseKingdom implements IRulerKingdom {

    private String kingName;
    private RulerStatus rulerStatus;

    public Space(String kingdomName, String emblem, Set<BaseKingdom> allies) {
        this.kingdomName = kingdomName;
        this.emblem = emblem;
        this.allies = allies;
        validateAllies();
    }

    public Space(String kingdomName, String emblem, Set<BaseKingdom> allies, String kingName) {
        this.kingdomName = kingdomName;
        this.emblem = emblem;
        this.kingName = kingName;
        this.allies = allies;
        rulerStatus = getRulerStatus();
    }

    @Override
    public String getKingName() {
        return kingName;
    }

    @Override
    public void addAlly(BaseKingdom kingdom) {
        super.addAlly(kingdom);
        getRulerStatus();
    }

    @Override
    public void removeAlly(BaseKingdom kingdom) {
        super.removeAlly(kingdom);
        getRulerStatus();
    }

    @Override
    public SecretMessage sendSecretMessage(BaseKingdom receiverKingdom, String secretMessage) {
        return new SecretMessage(this, receiverKingdom, secretMessage);
    }

    @Override
    public RulerStatus getRulerStatus() {
        if (allies.size() >= REQUIRED_ALLIES)
            setRulerStatus(RulerStatus.IS_RULER);
        else
            setRulerStatus(RulerStatus.NOT_RULER);
        return rulerStatus;
    }

    @Override
    public void setRulerStatus(RulerStatus rulerStatus) {
        this.rulerStatus = rulerStatus;
    }

}
