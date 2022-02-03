package com.goldencrown.services;

import java.util.HashMap;

import com.goldencrown.entities.BaseKingdom;

public class DecipherMessageService implements IDecipherMessageService {

    /**
     * Core business logic of problem
     * Deciphers secret message in O(N) Time; O(K) Space
     * where N is length of message string & K is length of emblem
     */
    @Override
    public boolean decipherSecretMessage(BaseKingdom receiverKingdom, String message) {

        // Build hash map of letters in emblem with their occurence
        HashMap<Character, Integer> emblemMap = getEmblemMap(receiverKingdom);
        message = message.toUpperCase();

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
        String emblem = receiverKingdom.getEmblem().toUpperCase();
        Integer cipherKey = emblem.length();

        final char ASCII_UPPER_BOUND = 'Z';
        final Integer ASCII_BOUND_CORRECTION = 26;

        for (Character character : emblem.toCharArray()) {

            // Move character forward by cipher-key units and check for ASCII leakage
            character = (char) (character + cipherKey);
            if (character > ASCII_UPPER_BOUND)
                character = (char) (character - ASCII_BOUND_CORRECTION);

            emblemMap.put(character, emblemMap.getOrDefault(character, 0) + 1);
        }

        return emblemMap;
    }

}
