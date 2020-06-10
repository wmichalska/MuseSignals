package com.michalska.signals.model.DTO;

import com.michalska.signals.model.entity.SampleEntity;

import java.time.LocalDateTime;

public class SampleDTO {
    private Long id;

    private LocalDateTime dateTime;

    private Float alpha;

    private Float beta;

    private Float delta;

    private Float gamma;

    private Float theta;

    private Long personId;

    public SampleDTO(Long id, LocalDateTime dateTime, Float alpha, Float beta, Float delta, Float gamma, Float theta, Long personId) {
        this.id = id;
        this.dateTime = dateTime;
        this.alpha = alpha;
        this.beta = beta;
        this.delta = delta;
        this.gamma = gamma;
        this.theta = theta;
        this.personId = personId;
    }

    public SampleDTO(SampleEntity sampleFromDb) {
        this.id = sampleFromDb.getId();
        this.dateTime = sampleFromDb.getDateTime();
        this.alpha = sampleFromDb.getAlpha();
        this.beta = sampleFromDb.getBeta();
        this.delta = sampleFromDb.getDelta();
        this.gamma = sampleFromDb.getGamma();
        this.theta = sampleFromDb.getTheta();
        this.personId = sampleFromDb.getPerson().getId();
    }

    public Long getId() {
        return id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Float getAlpha() {
        return alpha;
    }

    public void setAlpha(Float alpha) {
        this.alpha = alpha;
    }

    public Float getBeta() {
        return beta;
    }

    public void setBeta(Float beta) {
        this.beta = beta;
    }

    public Float getDelta() {
        return delta;
    }

    public void setDelta(Float delta) {
        this.delta = delta;
    }

    public Float getGamma() {
        return gamma;
    }

    public void setGamma(Float gamma) {
        this.gamma = gamma;
    }

    public Float getTheta() {
        return theta;
    }

    public void setTheta(Float theta) {
        this.theta = theta;
    }
}
