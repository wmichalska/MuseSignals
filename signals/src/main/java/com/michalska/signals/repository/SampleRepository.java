package com.michalska.signals.repository;

import com.michalska.signals.model.entity.SampleEntity;
import javassist.tools.rmi.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends CrudRepository<SampleEntity, Long> {
    public Sample findById(int id);
}
