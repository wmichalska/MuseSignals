package com.michalska.signals.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PERSON")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_name")
    private String name;

    @Column(name = "person_surname")
    private String surname;

    @Column(name = "person_age")
    private int age;

    @OneToMany(mappedBy = "person")
    private List<SampleEntity> samples;

    public PersonEntity() {

    }

    public PersonEntity(Long id, String name, String surname, int age, List<SampleEntity> samples) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.samples = samples;
    }

    public void setSamples(List<SampleEntity> samples) {
        this.samples = samples;
    }

    public List<SampleEntity> getSamples() {
        return samples;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
}
