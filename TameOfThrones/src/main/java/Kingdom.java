import java.util.*;

/**
 * Core Abstract  class to define attributes of every kingdom in "Southeros".
 * Extension classes for each kingdom to implement their variation of "decipher" method as per their attributes.
 * For our problem statement, Kingdom Space is default message sender to other kingdom;
 * Hence it implements additional attributes in comparison to other kingdoms.
 */

public abstract class Kingdom {
    String name;
    String symbol;
    int cipherKey;
    List<Kingdom> allies;
    DecipherMessage decipherMessage;
    int minAlliesRequired;

    @Override
    public boolean equals(Object kingdom) {
        if (kingdom == null)
            return false;
        if (! (kingdom instanceof Kingdom))
            return false;
        if (kingdom == this)
            return true;
        return this.name.equals(((Kingdom) kingdom).name);
    }

    public void addAlly(Kingdom kingdom) {
        if (!allies.contains(kingdom) && !kingdom.equals(this))
            allies.add(kingdom);
    }
}

class Space extends Kingdom implements KingdomService {
    Space() {
        name = "SPACE";
        symbol = "GORILLA";
        cipherKey = generateCipherKey();
        decipherMessage = new DecipherMessage();
        allies = new ArrayList<>();
        minAlliesRequired = 3;
    }

    @Override
    public int generateCipherKey() {
        return symbol.length();
    }

    @Override
    public OutputObject decipherSecretMessage(InputObject inputObject) {
        return new OutputObject(new Space(), decipherMessage.decipher(inputObject, new Space()));
    }
}

class Air extends Kingdom implements KingdomService {
    Air() {
        name = "AIR";
        symbol = "OWL";
        cipherKey = generateCipherKey();
        decipherMessage = new DecipherMessage();
    }

    @Override
    public int generateCipherKey() {
        return symbol.length();
    }

    @Override
    public OutputObject decipherSecretMessage(InputObject inputObject) {
        return new OutputObject(new Air(), decipherMessage.decipher(inputObject, new Air()));
    }

}

class Water extends Kingdom implements KingdomService {
    Water() {
        name = "WATER";
        symbol = "OCTOPUS";
        cipherKey = generateCipherKey();
        decipherMessage = new DecipherMessage();
    }

    @Override
    public int generateCipherKey() {
        return symbol.length();
    }

    @Override
    public OutputObject decipherSecretMessage(InputObject inputObject) {
        return new OutputObject(new Water(), decipherMessage.decipher(inputObject, new Water()));
    }
}

class Land extends Kingdom implements KingdomService {
    Land() {
        name = "LAND";
        symbol = "PANDA";
        cipherKey = generateCipherKey();
        decipherMessage = new DecipherMessage();
    }

    @Override
    public int generateCipherKey() {
        return symbol.length();
    }

    @Override
    public OutputObject decipherSecretMessage(InputObject inputObject) {
        return new OutputObject(new Land(), decipherMessage.decipher(inputObject, new Land()));
    }
}

class Ice extends Kingdom implements KingdomService {
    Ice() {
        name = "ICE";
        symbol = "MAMMOTH";
        cipherKey = generateCipherKey();
        decipherMessage = new DecipherMessage();
    }

    @Override
    public int generateCipherKey() {
        return symbol.length();
    }

    @Override
    public OutputObject decipherSecretMessage(InputObject inputObject) {
        return new OutputObject(new Ice(), decipherMessage.decipher(inputObject, new Ice()));
    }
}

class Fire extends Kingdom implements KingdomService {
    Fire() {
        name = "FIRE";
        symbol = "DRAGON";
        cipherKey = generateCipherKey();
        decipherMessage = new DecipherMessage();
    }

    @Override
    public int generateCipherKey() {
        return symbol.length();
    }

    @Override
    public OutputObject decipherSecretMessage(InputObject inputObject) {
        return new OutputObject(new Fire(), decipherMessage.decipher(inputObject, new Fire()));
    }
}
