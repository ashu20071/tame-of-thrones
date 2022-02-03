package com.goldencrown;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Integration Test1; App should print ruler kingdom with their allies")
    public void appTest_shouldPrintRulerKingdom_withTheirAllies() {
        String expected = "SPACE FIRE AIR WATER";
        App.main(new String[] { "sample_input/input1.txt" });
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Integration Test2; App should print NONE if allies are less than 3")
    public void appTest_shouldPrintNONE_whenAlliesAreLessThanThree() {
        String expected = "NONE";
        App.main(new String[] { "sample_input/input3.txt" });
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
