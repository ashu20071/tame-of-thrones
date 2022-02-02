package com.goldencrown.exceptions;

public class InvalidKingdomException extends Exception {

    @Override
    public String toString() {
        return "Invalid kingdom passed";
    }
}
