package com.goldencrown.entities.Kingdoms;

import java.util.Set;

import com.goldencrown.entities.BaseKingdom;

public class Ice extends BaseKingdom {

    public Ice(String kingdomName, String emblem, Set<BaseKingdom> allies) {
        this.kingdomName = kingdomName;
        this.emblem = emblem;
        this.allies = allies;
        validateAllies();
    }
    
}
