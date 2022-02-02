package com.goldencrown.entities;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class BaseKingdom {
    protected String kingdomName;
    protected String emblem;
    protected Set<BaseKingdom> allies;

    public String getKingdomName() {
        return kingdomName;
    }

    public String getEmblem() {
        return emblem;
    }

    public Set<BaseKingdom> getAllies() {
        return new LinkedHashSet<>(allies);
    }

    protected void validateAllies() {
        if (allies.contains(this))
            allies.remove(this);

        allies.forEach(ally -> {
            ally.addAlly(this);
        });
    }

    public void addAlly(BaseKingdom kingdom) {
        if (!allies.contains(kingdom) && !kingdom.equals(this)) {
            allies.add(kingdom);
            kingdom.addAlly(this);
        }
    }

    public void removeAlly(BaseKingdom kingdom) {
        if (allies.contains(kingdom)) {
            allies.remove(kingdom);
            kingdom.removeAlly(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emblem == null) ? 0 : emblem.hashCode());
        result = prime * result + ((kingdomName == null) ? 0 : kingdomName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseKingdom other = (BaseKingdom) obj;
        if (emblem == null) {
            if (other.emblem != null)
                return false;
        } else if (!emblem.equals(other.emblem))
            return false;
        if (kingdomName == null) {
            if (other.kingdomName != null)
                return false;
        } else if (!kingdomName.equals(other.kingdomName))
            return false;
        return true;
    }

}
