package com.goldencrown.services;

import java.util.HashMap;

import com.goldencrown.entities.BaseKingdom;

public class DecipherMessageService {

    private final Character ASCII_LOWER_BOUND = 'A';
    private final Integer ASCII_BOUND_CORRECTION = 26;

    public boolean decipherSecretMessage(BaseKingdom receiverKingdom, String message) {
        HashMap<Character, Integer> emblemMap = getEmblemMap(receiverKingdom);

        for (Character character : message.toCharArray()) {
            if (emblemMap.containsKey(character)) {
                emblemMap.put(character, emblemMap.get(character) - 1);
                if (emblemMap.get(character) == 0)
                    emblemMap.remove(character);
            }
            if (emblemMap.size() == 0)
                return true;
        }

        return false;
    }

    private HashMap<Character, Integer> getEmblemMap(BaseKingdom receiverKingdom) {
        HashMap<Character, Integer> emblemMap = new HashMap<>();
        String emblem = receiverKingdom.getEmblem();
        Integer cipherKey = emblem.length();

        for (Character character : emblem.toCharArray()) {
            character = (char) (character - cipherKey);
            if (character < ASCII_LOWER_BOUND)
                character = (char) (character + ASCII_BOUND_CORRECTION);

            emblemMap.put(character, emblemMap.getOrDefault(character, 0) + 1);
        }

        return emblemMap;
    }

}
