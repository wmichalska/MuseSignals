package com.michalska.signals.services;

import com.michalska.signals.model.DTO.SampleDTO;
import com.michalska.signals.model.entity.SampleEntity;
import com.michalska.signals.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    public List<SampleDTO> getAllSamples() {
        List<SampleDTO> persons = new ArrayList<>();
        sampleRepository.findAll().forEach(sampleFromDb -> persons.add(new SampleDTO(sampleFromDb)));
        return persons;
    }

    public SampleEntity getSampleById(long id) {
        return sampleRepository.findById(id).get();
    }

    public SampleEntity saveOrUpdate(SampleEntity person) {
        return sampleRepository.save(person);
    }

    public void delete(long id) {
        sampleRepository.deleteById(id);
    }

    public void deleteAll() {
        sampleRepository.findAll().forEach(sample -> delete(sample.getId()));
    }
}
