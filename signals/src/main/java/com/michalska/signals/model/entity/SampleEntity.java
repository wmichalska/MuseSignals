package com.michalska.signals.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SAMPLE")
public class SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sample_dateTime")
    private LocalDateTime dateTime;

    @Column(name = "sample_alpha")
    private Float alpha;

    @Column(name = "sample_beta")
    private Float beta;

    @Column(name = "sample_delta")
    private Float delta;

    @Column(name = "sample_gamma")
    private Float gamma;

    @Column(name = "sample_theta")
    private Float theta;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public SampleEntity() {
    }

    public SampleEntity(LocalDateTime dateTime, Float alpha, Float beta, Float delta, Float gamma, Float theta) {

        this.dateTime = dateTime;
        this.alpha = alpha;
        this.beta = beta;
        this.delta = delta;
        this.gamma = gamma;
        this.theta = theta;

    }

    public SampleEntity(LocalDateTime dateTime, Float alpha, Float beta, Float delta, Float gamma, Float theta, PersonEntity person) {

        this.dateTime = dateTime;
        this.alpha = alpha;
        this.beta = beta;
        this.delta = delta;
        this.gamma = gamma;
        this.theta = theta;
        this.person = person;

    }

    public boolean isComplete() {
        if (dateTime != null && alpha != null && beta != null && gamma != null && delta != null && theta != null
        && alpha != 0f && beta != 0f && gamma != 0f && delta != 0f && theta != 0f)
            return true;
        else
            return false;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setAlpha(Float alpha) {
        this.alpha = alpha;
    }

    public void setBeta(Float beta) {
        this.beta = beta;
    }

    public void setDelta(Float delta) {
        this.delta = delta;
    }

    public void setGamma(Float gamma) {
        this.gamma = gamma;
    }

    public void setTheta(Float theta) {
        this.theta = theta;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Float getAlpha() {
        return alpha;
    }

    public Float getBeta() {
        return beta;
    }

    public Float getDelta() {
        return delta;
    }

    public Float getGamma() {
        return gamma;
    }

    public Float getTheta() {
        return theta;
    }


}
