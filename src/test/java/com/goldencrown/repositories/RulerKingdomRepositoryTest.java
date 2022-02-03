package com.goldencrown.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.goldencrown.entities.IRulerKingdom;
import com.goldencrown.entities.Kingdoms.Space;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RulerKingdomRepositoryTest {

    private RulerKingdomRepository rulerKingdomRepository;

    @BeforeEach
    public void setup() {
        Map<String, IRulerKingdom> rulerKingdomMap = new LinkedHashMap<>();
        rulerKingdomMap.put("SPACE", new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN"));
        rulerKingdomRepository = new RulerKingdomRepository(rulerKingdomMap);
    }

    @Test
    @DisplayName("getKingdomByName method should return kingdom given valid name")
    public void getKingdomByName_shouldReturnKingdom_GivenValidName() {
        IRulerKingdom expected = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        Assertions.assertEquals(expected, rulerKingdomRepository.getKingdomByName("SPACE"));
    }

    @Test
    @DisplayName("registerKingdom method should register given kingdom in repositroy")
    public void registerKingdom_shouldRegisterKingdomInRepository() {
        // pre checks
        rulerKingdomRepository = new RulerKingdomRepository();        
        Assertions.assertNull(rulerKingdomRepository.getKingdomByName("SPACE"));

        // actual test
        IRulerKingdom expected = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        rulerKingdomRepository.registerKingdom(expected);
        Assertions.assertEquals(expected, rulerKingdomRepository.getKingdomByName("SPACE"));    
    }

    @Test
    @DisplayName("deleteKingdom method should delete kingdom from repository")
    public void deleteKingdom_shouldDeleteKingdomFromRepository() {
        // pre checks
        IRulerKingdom expected = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        Assertions.assertEquals(expected, rulerKingdomRepository.getKingdomByName("SPACE"));    

        // actual test
        rulerKingdomRepository.deleteKingdom(expected);
        Assertions.assertNull(rulerKingdomRepository.getKingdomByName("SPACE"));
    }

    @Test
    @DisplayName("getKingdomByName should return null given invalid name")
    public void getKingdomByName_shouldReturnNull_GivenInvalidKingdomName() {
        Assertions.assertNull(rulerKingdomRepository.getKingdomByName("INVALID NAME"));
    }

    @Test
    @DisplayName("getAllKingdoms method should return list of all kingdoms in repository")
    public void getAllKingdom_shouldReturnListOfAllKingdomInRepository() {
        IRulerKingdom space = new Space("SPACE", "GORILLA", new LinkedHashSet<>(), "SHAN");
        List<IRulerKingdom> expected = new ArrayList<>(Arrays.asList(space));

        Assertions.assertEquals(expected, rulerKingdomRepository.getAllKingdoms());
    }
}
