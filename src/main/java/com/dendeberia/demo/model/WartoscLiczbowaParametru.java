package com.dendeberia.demo.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@JsonTypeName("wartoscLiczbowaParametru")
public class WartoscLiczbowaParametru extends WartoscParametru{
    private BigDecimal wartosc;
    private Jednostka jednostka;

    public WartoscLiczbowaParametru() {
    }

    public WartoscLiczbowaParametru(BigDecimal wartosc, Jednostka jednostka) {
        this.wartosc = wartosc;
        this.jednostka = jednostka;
    }

    public BigDecimal getWartosc() {
        return wartosc;
    }

    public void setWartosc(BigDecimal wartosc) {
        this.wartosc = wartosc;
    }

    public Jednostka getJednostka() {
        return jednostka;
    }

    public void setJednostka(Jednostka jednostka) {
        this.jednostka = jednostka;
    }
}
