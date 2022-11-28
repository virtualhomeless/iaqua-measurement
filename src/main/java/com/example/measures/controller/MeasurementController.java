package com.example.measures.controller;

import com.example.measures.model.Measurement;
import com.example.measures.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/all")
    public List<Measurement> getAllMeasurements(){
        return measurementService.getAllMeasurementsOfEntireSystem();
    }
    @GetMapping("/{idMeasure}")
    public Measurement getMeasurementById(@PathVariable Long idMeasure){
        return measurementService.getMeasurementById(idMeasure);
    }
    @GetMapping("/status/{statusMeasure}")
    public List<Measurement> getMeasurementByStatus(@PathVariable("statusMeasure") String statusMeasure){
        return measurementService.getAllMeasurementsByStatus(statusMeasure);
    }
    @GetMapping("/operator/{idOperator}")
    public List<Measurement> getMeasurementByOperator(@PathVariable("idOperator") Long idOperator){
        return measurementService.getAllMeasurementsByOperator(idOperator);
    }
    @GetMapping("/plant/{idPlant}")
    public List<Measurement> getMeasurementByPlant(@PathVariable("idPlant") Long idPlant){
        return measurementService.getAllMeasurementsByPlant(idPlant);
    }
    @GetMapping("/measures")
    public void getMExcel() throws Exception {
        measurementService.getExcel();
    }
    @PostMapping("/add")
    public Measurement addMeasurement(@RequestBody Measurement newMeasurement){
        return measurementService.addMeasurement(newMeasurement);
    }

}
