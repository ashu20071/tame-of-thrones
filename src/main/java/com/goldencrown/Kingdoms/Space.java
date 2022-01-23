package com.goldencrown.Kingdoms;

import java.util.ArrayList;

public class Space extends BaseKingdom {

    private static final Integer MIN_ALLIES_REQUIRED = 3;

    public Space() {
        this.name = "SPACE";
        this.symbol = "GORILLA";
        this.cipherKey = generateCipherKey();
        this.allies = new ArrayList<>();
    }

    @Override
    protected Integer generateCipherKey() {
        return this.symbol.length();
    }

}
