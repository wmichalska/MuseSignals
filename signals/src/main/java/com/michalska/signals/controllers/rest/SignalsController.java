package com.michalska.signals.controllers.rest;

import com.michalska.signals.controllers.MuseController;
import com.michalska.signals.model.entity.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignalsController {

    @Autowired
    private PersonController personController;

    @Autowired
    private SampleController sampleController;

    Logger logger = LoggerFactory.getLogger(SignalsController.class);

    @PostMapping("/start")
    public PersonEntity start(@RequestBody PersonEntity person) {
        PersonEntity activePerson = MuseController.getInstance().getActivePerson();

        // check if examination is already ongoing
        if (activePerson != null) {
            logger.warn("Examination for " + activePerson.getName() + " " + activePerson.getSurname() + " is already ongoing!");
            return activePerson;
        }

        activePerson = personController.getPersonService().saveOrUpdate(person);

        logger.info("Added new person to database: " + activePerson.getName() + " " + activePerson.getSurname());

        MuseController.getInstance().setActivePerson(activePerson);
        MuseController.getInstance().setSampleController(sampleController);
        MuseController.getInstance().start();

        return activePerson;
    }

    @PostMapping("/stop")
    public void stop() {
        MuseController.getInstance().stop();
    }

    @PostMapping("/reset")
    public void reset() {
        stop();
        logger.info("All samples and people removed from database");
        sampleController.getSampleService().deleteAll();
        personController.getPersonService().deleteAll();
    }

    // for test purposes
    public PersonController getPersonController() {
        return personController;
    }
}
