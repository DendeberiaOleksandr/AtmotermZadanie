package com.dendeberia.demo.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Parametr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 50, message = "Parametr name should be less than 50 symbols")
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<WartoscParametru> wartoscParametruList;

    public Parametr() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WartoscParametru> getWartoscParametruList() {
        return wartoscParametruList;
    }

    public void setWartoscParametruList(List<WartoscParametru> wartoscParametruList) {
        this.wartoscParametruList = wartoscParametruList;
    }
}
