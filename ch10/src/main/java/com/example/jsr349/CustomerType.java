package com.example.jsr349;

public enum CustomerType {

    INDIVIDUAL("I"), CORPORATE("C");

    private String code;

    CustomerType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
