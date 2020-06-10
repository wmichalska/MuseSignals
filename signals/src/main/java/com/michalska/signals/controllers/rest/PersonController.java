package com.michalska.signals.controllers.rest;

import com.michalska.signals.model.DTO.PersonDTO;
import com.michalska.signals.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonService getPersonService() {
        return personService;
    }

    @GetMapping("/sessions")
    private List<PersonDTO> getAllPeople() {
        int amountOfExaminedPeople = personService.getAllPeople().size();
        if (amountOfExaminedPeople > 0) {
            logger.info("Number of sessions: " + personService.getAllPeople().size() +
                    ", Last examined person: " + personService.getPersonById(amountOfExaminedPeople).getName());
        } else {
            logger.warn("List of examined people is empty");
        }
        return personService.getAllPeople();
    }
//    @GetMapping("/sessions/{id}")
//    private PersonDTO getPersonById() {
//        return personService.getPersonById(id);
//        }
//    }

}
