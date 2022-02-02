package com.goldencrown.repositories;

import java.util.List;

import com.goldencrown.entities.BaseKingdom;

public interface IBaseKindgomRepository {
    BaseKingdom getKingdomByName(String kingdomName);

    void registerKingdom(BaseKingdom kingdom);

    void deleteKingdom(BaseKingdom kingdom);

    List<BaseKingdom> getAllKingdoms();
}
