package com.goldencrown.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldencrown.entities.IRulerKingdom;

public class RulerKingdomRepository implements IRulerKingdomRepository {

    private final Map<String, IRulerKingdom> rulerKingdomMap;

    public RulerKingdomRepository() {
        this.rulerKingdomMap = new HashMap<>();
    }

    public RulerKingdomRepository(Map<String, IRulerKingdom> rulerKingdomMap) {
        this.rulerKingdomMap = rulerKingdomMap;
    }

    @Override
    public IRulerKingdom getKingdomByName(String kingdomName) {        
        return rulerKingdomMap.get(kingdomName);
    }

    @Override
    public void registerKingdom(IRulerKingdom kingdom) {
        rulerKingdomMap.put(kingdom.getKingdomName(), kingdom);
        
    }

    @Override
    public void deleteKingdom(IRulerKingdom kingdom) {
        rulerKingdomMap.remove(kingdom.getKingdomName());    
    }

    @Override
    public List<IRulerKingdom> getAllKingdoms() {        
        return new ArrayList<>(rulerKingdomMap.values());
    }
    
}
