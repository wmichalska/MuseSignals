package com.michalska.signals.services;

import com.michalska.signals.controllers.rest.SampleController;
import com.michalska.signals.model.entity.PersonEntity;
import com.michalska.signals.model.entity.SampleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class SampleConsumer implements Runnable {
    SampleService sampleService;

    private BlockingQueue<SampleEntity> samples;

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    private PersonEntity person;

    public SampleConsumer(BlockingQueue<SampleEntity> queue, SampleService ss, PersonEntity person) {
        this.samples = queue;
        this.person = person;
        this.sampleService = ss;
    }

    public void run() {
        try {
            while (true) {
                SampleEntity sample = samples.take();
                sample.setPerson(person);
                logger.info("Consumed sample: alpha: " + sample.getAlpha() + ", beta: " + sample.getBeta() + ", gamma: " + sample.getGamma() + ", delta: " + sample.getDelta() + " theta: " + sample.getTheta());
                sampleService.saveOrUpdate(sample);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}