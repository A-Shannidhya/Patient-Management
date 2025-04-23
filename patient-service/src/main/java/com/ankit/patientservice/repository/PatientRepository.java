/*
 *
 *  * Copyright (c) ${YEAR} Ayshi Shannidhya Panda
 *  * File: ${NAME}
 *  * Author: ${USER}
 *  * Created on: ${DATE} at ${TIME}
 *
 *
 */

package com.ankit.patientservice.repository;

import com.ankit.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, UUID id);
}
