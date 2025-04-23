/*
 *
 *  * Copyright (c) 2025 Ayshi Shannidhya Panda
 *  * File: PatientController.java
 *  * Author: Ankit
 *  * Created on: April 23, 2025 at 01:51 pm
 *
 *
 */
/*
 *
 *  * Copyright (c) 2025 Ayshi Shannidhya Panda
 *  * File: PatientController.java
 *  * Author: Ankit
 *  * Created on: April 22, 2025 at 08:20 pm
 *
 *
 */
/*
 *
 *  * Copyright (c) 2025 Ayshi Shannidhya Panda
 *  * File: PatientController.java
 *  * Author: Ankit
 *  * Created on: April 22, 2025 at 08:17 pm
 *
 *
 */
/*
 *
 *  * Copyright (c) 2025 Ayshi Shannidhya Panda
 *  * File: PatientController.java
 *  * Author: Ankit
 *  * Created on: April 22, 2025 at 08:16 pm
 *
 *
 */
/*
 *
 *  * Copyright (c) 2025 Ayshi Shannidhya Panda
 *  * File: PatientController.java
 *  * Author: Ankit
 *  * Created on: April 21, 2025 at 01:52 pm
 *
 *
 */
/*
 *
 *  * Copyright (c) 2025 Ayshi Shannidhya Panda
 *  * File: PatientController.java
 *  * Author: Ankit
 *  * Created on: April 21, 2025 at 01:52 pm
 *
 *
 */
/*
 *
 *  * Copyright (c) ${YEAR} Ayshi Shannidhya Panda
 *  * File: ${NAME}
 *  * Author: ${USER}
 *  * Created on: ${DATE} at ${TIME}
 *
 *
 */

package com.ankit.patientservice.Controller;

import com.ankit.patientservice.DTO.PatientRequestDTO;
import com.ankit.patientservice.DTO.PatientResponseDTO;
import com.ankit.patientservice.DTO.validators.CreatePatientValidationGroup;
import com.ankit.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient Controller", description = "API for managing Patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/get")
    @Operation(summary = "Get all patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatient() {

        List<PatientResponseDTO> patientResponseDTOS = patientService.getPatient();
        return ResponseEntity.ok().body(patientResponseDTOS);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a patient")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Builder.Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                            @Validated({Builder.Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id,
                patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }

}
