package com.goldencrown.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldencrown.entities.BaseKingdom;

public class BaseKingdomRepository implements IBaseKindgomRepository {
    private final Map<String, BaseKingdom> baseKingdomMap;

    public BaseKingdomRepository() {
        this.baseKingdomMap = new HashMap<>();
    }

    public BaseKingdomRepository(Map<String, BaseKingdom> kingdomMap) {
        this.baseKingdomMap = kingdomMap;
    }

    @Override
    public BaseKingdom getKingdomByName(String kingdomName) {
        return baseKingdomMap.get(kingdomName);
    }

    @Override
    public void registerKingdom(BaseKingdom kingdom) {
        baseKingdomMap.put(kingdom.getKingdomName(), kingdom);
    }

    @Override
    public void deleteKingdom(BaseKingdom kingdom) {
        baseKingdomMap.remove(kingdom.getKingdomName());
    }

    @Override
    public List<BaseKingdom> getAllKingdoms() {
        return new ArrayList<>(baseKingdomMap.values());
    }

}
