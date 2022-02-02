package com.goldencrown.repositories;

import java.util.List;

import com.goldencrown.entities.IRulerKingdom;

public interface IRulerKingdomRepository {
    IRulerKingdom getKingdomByName(String kingdomName);

    void registerKingdom(IRulerKingdom kingdom);

    void deleteKingdom(IRulerKingdom kingdom);

    List<IRulerKingdom> getAllKingdoms();
}
