package com.dendeberia.demo.model;

public enum Jednostka {
    m3("m3"), kg("kg"), Mg("Mg");

    public final String name;

    Jednostka(String name) {
        this.name = name;
    }
}
