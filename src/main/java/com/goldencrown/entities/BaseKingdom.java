package com.goldencrown.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseKingdom {
    protected String kingdomName;
    protected String emblem;
    protected List<BaseKingdom> allies;

    public String getKingdomName() {
        return kingdomName;
    }

    public String getEmblem() {
        return emblem;
    }

    public List<BaseKingdom> getAllies() {
        return new ArrayList<>(allies);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allies == null) ? 0 : allies.hashCode());
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
        if (allies == null) {
            if (other.allies != null)
                return false;
        } else if (!allies.equals(other.allies))
            return false;
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
