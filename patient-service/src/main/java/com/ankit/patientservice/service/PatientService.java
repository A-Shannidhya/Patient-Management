/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Patient Management
 * Author: Ayshi Shannidhya Panda
 * Created on: 2025-6-10
 */

package com.ankit.patientservice.service;

import com.ankit.patientservice.DTO.PatientRequestDTO;
import com.ankit.patientservice.DTO.PatientResponseDTO;
import com.ankit.patientservice.exception.EmailAlreadyExistsException;
import com.ankit.patientservice.exception.PatientNotFoundException;
import com.ankit.patientservice.grpc.BillingServiceGrpcClient;
import com.ankit.patientservice.mapper.PatientMapper;
import com.ankit.patientservice.model.Patient;
import com.ankit.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          BillingServiceGrpcClient billingServiceGrpcClient,
                          kafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    public List<PatientResponseDTO> getPatient() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDTO.getEmail());
        }

        Patient patient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO)
        );

        billingServiceGrpcClient.createBillingAccount(
                patient.getId().toString(),
                patient.getName(),
                patient.getEmail()
        );

        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
