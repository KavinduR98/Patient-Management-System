package com.ushan.patient_sevice.service;

import com.ushan.patient_sevice.dto.PatientResponseDTO;
import com.ushan.patient_sevice.mapper.PatientMapper;
import com.ushan.patient_sevice.model.Patient;
import com.ushan.patient_sevice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(patient -> PatientMapper.toDTO(patient)).toList();
    }
}
