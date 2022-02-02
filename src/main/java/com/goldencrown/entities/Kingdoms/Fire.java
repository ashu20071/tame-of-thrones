package com.goldencrown.entities.Kingdoms;

import java.util.Set;

import com.goldencrown.entities.BaseKingdom;

public class Fire extends BaseKingdom {

    public Fire(String kingdomName, String emblem, Set<BaseKingdom> allies) {
        this.kingdomName = kingdomName;
        this.emblem = emblem;
        this.allies = allies;
        validateAllies();
    }

}
