package com.michalska.signals;

import com.michalska.signals.controllers.MuseController;
import com.michalska.signals.controllers.rest.SignalsController;
import com.michalska.signals.model.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SignalsControllerTest {
    @Autowired
    private SignalsController signalsController;

    @Test
    public void startSuccessfulTest() {
        PersonEntity person = new PersonEntity((long) 0, "Weronika", "Michalska", 25, null);
        PersonEntity savedPerson = signalsController.start(person);


        assertThat(MuseController.getInstance() != null);
        assertThat(savedPerson == person);
        assertThat(savedPerson.getId() == 1);
    }

    @Test
    public void startFailedExaminationOngoingTest() {
        PersonEntity person = new PersonEntity((long) 0, "Weronika", "Michalska", 25, null);
        PersonEntity savedPerson = signalsController.start(person);


        PersonEntity newPerson = new PersonEntity((long) 0, "Adrian", "Nowak", 13, null);
        PersonEntity nextPerson = signalsController.start(newPerson);

        assertThat(MuseController.getInstance() != null);
        assertThat(savedPerson != nextPerson);
        assertThat(savedPerson.getId() == 1);
        assertThat(nextPerson == null);
    }

//    @Test
//    public void resetAllTest() {
//        PersonEntity person = new PersonEntity((long) 0, "Weronika", "Michalska", 25, null);
//        PersonEntity savedPerson = signalsController.getPersonController().getPersonService().saveOrUpdate(person);
//
//        signalsController.reset();
//
////        assertThat(signalsController.getPersonController().getPersonService().getAllPeople() == null);
//    }
}