package com.goldencrown.repositories;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.goldencrown.entities.BaseKingdom;
import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Ice;
import com.goldencrown.entities.Kingdoms.Land;
import com.goldencrown.entities.Kingdoms.Water;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BaseKingdomRepositoryTest {
    private BaseKingdomRepository baseKingdomRepository;

    @BeforeEach
    public void setup() {
        Map<String, BaseKingdom> baseKingdomMap = new LinkedHashMap<>();
        baseKingdomMap.put("ICE", new Ice("ICE", "MAMMOTH", new LinkedHashSet<>()));
        baseKingdomMap.put("LAND", new Land("LAND", "PANDA", new LinkedHashSet<>()));
        baseKingdomMap.put("WATER", new Water("WATER", "OCTOPUS", new LinkedHashSet<>()));
        baseKingdomRepository = new BaseKingdomRepository(baseKingdomMap);
    }

    @Test
    @DisplayName("getKindgomByName should return kingdom given valid name")
    public void getKingdomByName_shouldReturnKingdom() {
        BaseKingdom expected = new Ice("ICE", "MAMMOTH", new LinkedHashSet<>());
        Assertions.assertEquals(expected, baseKingdomRepository.getKingdomByName("ICE"));
    }

    @Test
    @DisplayName("registerKingdom should register kindgom into repository")
    public void registerKingdom_shouldRegisterKingdomIntoRepository() {
        BaseKingdom expected = new Air("AIR", "OWL", new LinkedHashSet<>());
        baseKingdomRepository.registerKingdom(expected);
        Assertions.assertEquals(expected, baseKingdomRepository.getKingdomByName("AIR"));
    }

    @Test
    @DisplayName("deleteKingdom method should delete kingdom from repository")
    public void deleteKingdom_shouldDeleteKingdomFromRepository() {
        baseKingdomRepository.deleteKingdom(new Water("WATER", "OCTOPUS", new LinkedHashSet<>()));
        Assertions.assertNull(baseKingdomRepository.getKingdomByName("WATER"));
    }

    @Test
    @DisplayName("getAllKingdoms should return list of all kingdoms in repository")
    public void getAllKingdoms_shouldReturnListOfAllKingdomsInRepository() {
        BaseKingdom ice = new Ice("ICE", "MAMMOTH", new LinkedHashSet<>());
        BaseKingdom land = new Land("LAND", "PANDA", new LinkedHashSet<>());
        BaseKingdom water = new Water("WATER", "OCTOPUS", new LinkedHashSet<>());
        
        List<BaseKingdom> expected = new LinkedList<>(Arrays.asList(ice, land, water));
        Assertions.assertEquals(expected, baseKingdomRepository.getAllKingdoms());
    }
}
