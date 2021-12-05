package com.dendeberia.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = WartoscLiczbowaParametru.class, name = "wartoscLiczbowaParametru"),
        @JsonSubTypes.Type(value = WartoscOpisowaParametru.class, name = "wartoscOpisowaParametru")
})
public abstract class WartoscParametru {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Date dataOd;
    protected Date dataDo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Parametr parametr;

    public WartoscParametru() {
    }

    public WartoscParametru(Long id, Date dataOd, Date dataDo) {
        this.id = id;
        this.dataOd = dataOd;
        this.dataDo = dataDo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataOd() {
        return dataOd;
    }

    public void setDataOd(Date dataOd) {
        this.dataOd = dataOd;
    }

    public Date getDataDo() {
        return dataDo;
    }

    public void setDataDo(Date dataDo) {
        this.dataDo = dataDo;
    }

    public Parametr getParametr() {
        return parametr;
    }

    public void setParametr(Parametr parametr) {
        this.parametr = parametr;
    }
}
