package com.michalska.signals.controllers;


import com.michalska.signals.model.entity.PersonEntity;
import com.michalska.signals.controllers.rest.SampleController;
import com.michalska.signals.services.SampleConsumer;
import com.michalska.signals.model.entity.SampleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oscP5.OscEventListener;
import oscP5.OscP5;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MuseController {

    private static final MuseController INSTANCE = new MuseController();
    private OscP5 museServer = null;
    private boolean isServerRunning = false;
    private SampleEntity sample = new SampleEntity();
    private static final Logger logger = LoggerFactory.getLogger(MuseController.class);
    private SampleConsumer samplesConsumer = null;
    private SampleController sampleController = null;
    private PersonEntity activePerson = null;
    public BlockingQueue<SampleEntity> samples = new LinkedBlockingDeque<>();

    public void setSampleController(SampleController sampleController) {
        this.sampleController = sampleController;
    }

    public PersonEntity getActivePerson() {
        return activePerson;
    }

    public void setActivePerson(PersonEntity activePerson) {
        this.activePerson = activePerson;
    }

    private MuseController() {
    }

    public static MuseController getInstance() {
        return INSTANCE;
    }

    public void start() {
        if (museServer == null) {
            museServer = new OscP5(this, 8085);
        }

        if (samplesConsumer == null) {
            this.samplesConsumer = new SampleConsumer(MuseController.getInstance().samples, sampleController.getSampleService(), activePerson);
            Thread t = new Thread(samplesConsumer);
            t.start();
        } else {
            this.samplesConsumer.setPerson(activePerson);
        }

        if (!isServerRunning) {
            logger.debug("Starting muse controller");
            getInstance().museServer.addListener(msg -> {
                Object[] arguments = msg.arguments();

                Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
                sample.setDateTime(LocalDateTime.now());

                //Check for Alpha signal
                if (msg.checkAddrPattern("/muse/elements/alpha_absolute")) {
                    arguments = msg.arguments();
                    sample.setAlpha((Float) arguments[0]);
                }

                //Check for Beta signal
                if (msg.checkAddrPattern("/muse/elements/beta_absolute")) {
                    arguments = msg.arguments();
                    sample.setBeta((Float) arguments[0]);
                }
                //Check for Gamma signal
                if (msg.checkAddrPattern("/muse/elements/gamma_absolute")) {
                    arguments = msg.arguments();
                    sample.setGamma((Float) arguments[0]);
                }

                //Check for Delta signal
                if (msg.checkAddrPattern("/muse/elements/delta_absolute")) {
                    arguments = msg.arguments();
                    sample.setDelta((Float) arguments[0]);
                }

                //Check for Theta signal
                if (msg.checkAddrPattern("/muse/elements/theta_absolute")) {
                    arguments = msg.arguments();
                    sample.setTheta((Float) arguments[0]);
                }
                if (sample.isComplete()) {
                    pushIntoQueue(sample);
                    sample = new SampleEntity();
                }
            });
            isServerRunning = true;
        } else {
            logger.warn("MuseServer already running");

        }
    }

    public void pushIntoQueue(SampleEntity sample) {
        try {
            samples.put(sample);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (isServerRunning) {

            OscEventListener listener = getInstance().museServer.listeners().get(0);
            if (listener != null) {
                getInstance().museServer.removeListener(listener);
            }
            isServerRunning = false;
            logger.info("Muse controller is stopped. Collecting samples is finished.");
        } else {
            logger.warn("Muse controlled was stopped. You cant stop process of collecting samples, because it didn't start yet!");
        }
        activePerson = null;
    }
}
