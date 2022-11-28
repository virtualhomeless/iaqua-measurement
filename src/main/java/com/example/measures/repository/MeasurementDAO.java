package com.example.measures.repository;

import com.example.measures.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MeasurementDAO extends JpaRepository<Measurement, Long> {

}
