package com.goldencrown.exceptions;

public class InvalidMessageException extends Exception {

    @Override
    public String toString() {
        return "Invalid message passed";
    }
}
