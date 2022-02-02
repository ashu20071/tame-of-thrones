package com.goldencrown.services;

import java.util.LinkedHashSet;

import com.goldencrown.entities.Kingdoms.Air;
import com.goldencrown.entities.Kingdoms.Fire;
import com.goldencrown.entities.Kingdoms.Ice;
import com.goldencrown.entities.Kingdoms.Land;
import com.goldencrown.entities.Kingdoms.Water;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecipherMessageServiceTest {

    private final IDecipherMessageService decipherMessageService = new DecipherMessageService();

    @Test
    @DisplayName("Test against sample input 1 on Geektrust platform for correctness badge")
    public void decipherSecretMessageTest_sampleInput1() {
        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Air("AIR", "OWL", new LinkedHashSet<>()), "ROZO"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Land("LAND", "PANDA", new LinkedHashSet<>()),
                        "FAIJWJSOOFAMAU"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Ice("ICE", "MAMMOTH", new LinkedHashSet<>()),
                        "STHSTSTVSASOS"));
    }

    @Test
    @DisplayName("Test against sample input 2 on Geektrust platform for correctness badge")
    public void decipherSecretMessageTest_sampleInput2() {
        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Land("LAND", "PANDA", new LinkedHashSet<>()),
                        "FDIXXSOKKOFBBMU"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Ice("ICE", "MAMMOTH", new LinkedHashSet<>()),
                        "MOMAMVTMTMHTM"));

        Assertions.assertFalse(
                decipherMessageService.decipherSecretMessage(new Water("WATER", "OCTOPUS", new LinkedHashSet<>()),
                        "SUMMER IS COMING"));

        Assertions.assertFalse(
                decipherMessageService.decipherSecretMessage(new Air("AIR", "OWL", new LinkedHashSet<>()),
                        "OWLAOWLBOWLC"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Fire("FIRE", "DRAGON", new LinkedHashSet<>()),
                        "AJXGAMUTA"));

    }

    @Test
    @DisplayName("Test against sample input 3 on Geektrust platform for correctness badge")
    public void decipherSecretMessageTest_sampleInput3() {

        Assertions.assertFalse(
                decipherMessageService.decipherSecretMessage(new Air("AIR", "OWL", new LinkedHashSet<>()),
                        "OWLAOWLBOWLC"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Land("LAND", "PANDA", new LinkedHashSet<>()),
                        "OFBBMUFDICCSO"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Ice("ICE", "MAMMOTH", new LinkedHashSet<>()),
                        "VTBTBHTBBBOBAB"));

        Assertions.assertFalse(
                decipherMessageService.decipherSecretMessage(new Water("WATER", "OCTOPUS", new LinkedHashSet<>()),
                        "SUMMER IS COMING"));

    }

    @Test
    @DisplayName("Test against sample input 4 on Geektrust platform for correctness badge")
    public void decipherSecretMessageTest_sampleInput4() {

        Assertions.assertFalse(
                decipherMessageService.decipherSecretMessage(new Air("AIR", "OWL", new LinkedHashSet<>()),
                        "OWL"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Land("LAND", "PANDA", new LinkedHashSet<>()),
                        "FAIJWJSOOFAMAU"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Ice("ICE", "MAMMOTH", new LinkedHashSet<>()),
                        "STHSTSTVSASOS"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Fire("FIRE", "DRAGON", new LinkedHashSet<>()),
                        "JXGOOMUTOO"));

    }

    @Test
    @DisplayName("Test against sample input 5 on Geektrust platform for correctness badge")
    public void decipherSecretMessageTest_sampleInput5() {

        Assertions.assertFalse(
                decipherMessageService.decipherSecretMessage(new Land("LAND", "PANDA", new LinkedHashSet<>()),
                        "PANDAUFSI"));

        Assertions.assertFalse(
                decipherMessageService.decipherSecretMessage(new Ice("ICE", "MAMMOTH", new LinkedHashSet<>()),
                        "MAMMOTH THVAO"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Fire("FIRE", "DRAGON", new LinkedHashSet<>()),
                        "DRAGON JXGMUT"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Air("AIR", "OWL", new LinkedHashSet<>()),
                        "ZORRO"));

        Assertions.assertTrue(
                decipherMessageService.decipherSecretMessage(new Water("WATER", "OCTOPUS", new LinkedHashSet<>()),
                        "OCTO VJAVWBZ PUS"));

    }

}
