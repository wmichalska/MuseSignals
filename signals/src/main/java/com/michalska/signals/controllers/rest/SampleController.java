package com.michalska.signals.controllers.rest;

import com.michalska.signals.model.DTO.SampleDTO;
import com.michalska.signals.services.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SampleController {
    @Autowired
    SampleService sampleService;

    Logger logger = LoggerFactory.getLogger(SampleController.class);

    public SampleService getSampleService() {
        return sampleService;
    }

    @GetMapping("/samples")
    private List<SampleDTO> getAllSamples() {
        int amountOfSamples = sampleService.getAllSamples().size();
        if (amountOfSamples == 0) {
            logger.warn("List of samples is empty.");
        } else {
            logger.warn("Samples are stored in database.");
        }
        //maybe I shouldn't return about samples, it could be private
        return sampleService.getAllSamples();
    }
}
