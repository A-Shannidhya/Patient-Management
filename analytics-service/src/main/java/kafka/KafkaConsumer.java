/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Patient Management
 * Author: Ayshi Shannidhya Panda
 * Created on: 2025-6-15
 */

package kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consume(byte[] messageBytes) {
        try {
            // Debug raw bytes
            log.debug("Received raw bytes (hex): {}", bytesToHex(messageBytes));

            // Parse Protobuf message
            PatientEvent patientEvent = PatientEvent.parseFrom(messageBytes);

            // Log event details
            log.info("🩺 Patient event consumed:");
            log.info("➡️ ID: {}", patientEvent.getPatientId());
            log.info("➡️ Name: {}", patientEvent.getName());
            log.info("➡️ Email: {}", patientEvent.getEmail());
            log.info("➡️ Event Type: {}", patientEvent.getEventType());

            log.info("✅ Successfully parsed PatientEvent: {}", patientEvent);

            // TODO: Add DB persistence or analytics logic here

        } catch (InvalidProtocolBufferException e) {
            log.error("❌ Invalid Protobuf format for PatientEvent", e);
        } catch (Exception e) {
            log.error("❌ Unexpected error while consuming message", e);
        }
    }

    // Utility method to convert byte[] to hex
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }
}
