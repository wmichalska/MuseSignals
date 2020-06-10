package com.michalska.signals.services;

import com.michalska.signals.model.DTO.PersonDTO;
import com.michalska.signals.model.entity.PersonEntity;
import com.michalska.signals.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public List<PersonDTO> getAllPeople() {
        List<PersonDTO> people = new ArrayList<>();
        personRepository.findAll().forEach(personInDb -> people.add(
                new PersonDTO(personInDb.getId(), personInDb.getName(), personInDb.getSurname(), personInDb.getAge())
        ));

        return people;
    }

    public PersonEntity getPersonById(long id) {
        return personRepository.findById(id).get();
    }

    public PersonEntity saveOrUpdate(PersonEntity person) {
        return personRepository.save(person);
    }

    public void delete(long id) {
        personRepository.deleteById(id);
    }

    public void deleteAll() {
        personRepository.findAll().forEach(person -> delete(person.getId()));
    }


    public int countSamplesForPersonById(long id) {
        return personRepository.findById(id).get().getSamples().size();
    }
}
