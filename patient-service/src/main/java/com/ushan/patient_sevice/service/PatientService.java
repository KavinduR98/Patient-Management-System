package com.ushan.patient_sevice.service;

import com.ushan.patient_sevice.dto.PatientRequestDTO;
import com.ushan.patient_sevice.dto.PatientResponseDTO;
import com.ushan.patient_sevice.exception.EmailAlreadyExistsException;
import com.ushan.patient_sevice.mapper.PatientMapper;
import com.ushan.patient_sevice.model.Patient;
import com.ushan.patient_sevice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email " + patientRequestDTO.getEmail() + " already exists" );
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDTO(newPatient);
    }
}
