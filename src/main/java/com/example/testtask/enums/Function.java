package com.example.testtask.enums;

public enum Function {

    SUM("sum"),
    MIN("min"),
    MAX("max"),
    AVG("avg"),
    COUNT("count");

    private final String s;

    Function(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public static Function toFunction(String str) {
        for (Function f: Function.values()) {
            if (str.equals(f.getS())) {
                return f;
            }
        }
        return null;
    }
}
