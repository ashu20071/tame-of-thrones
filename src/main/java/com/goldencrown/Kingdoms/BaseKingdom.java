package com.goldencrown.Kingdoms;

import java.util.List;

public abstract class BaseKingdom {
    protected String name;
    protected String symbol;
    protected Integer cipherKey;
    protected List<BaseKingdom> allies;

    protected abstract Integer generateCipherKey();

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getCipherKey() {
        return cipherKey;
    }

    public List<BaseKingdom> getAllies() {
        return allies;
    }

    public void addAlly(BaseKingdom kingdom) {
        if (allies.contains(kingdom) || kingdom.getName().equals(name))
            throw new IllegalArgumentException("Invalid Kingdom");
        allies.add(kingdom);
    }

    @Override
    public String toString() {
        return "Kingdom [name=" + name + ", symbol=" + symbol + ", allies=" + allies
                + "]";
    }

}
