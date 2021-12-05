package com.dendeberia.demo.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@JsonTypeName("wartoscOpisowaParametru")
public class WartoscOpisowaParametru extends WartoscParametru{
    @Column(length = 30, name = "wartość")
    private String wartosc;

    public WartoscOpisowaParametru() {
    }

    public WartoscOpisowaParametru(String wartosc) {
        this.wartosc = wartosc;
    }

    public String getWartosc() {
        return wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }
}
