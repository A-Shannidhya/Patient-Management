/// *
// * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
// *
// * This source code is confidential and intended solely for internal use.
// * Unauthorized copying, modification, distribution, or disclosure of this
// * file, via any medium, is strictly prohibited.
// *
// * Project: Patient Management
// * Author: Ayshi Shannidhya Panda
// * Created on: 2025-6-14
// */
//
//package com.ankit.patientservice.kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//import patient.events.PatientEvent;
//
//@Service
//public class kafkaConsumer {
//
//    private static final Logger log = LoggerFactory.getLogger(kafkaConsumer.class);
//
//    @KafkaListener(topics = "patient", groupId = "patient-consumer")
//    public void consume(ConsumerRecord<String, byte[]> record) {
//        try {
//            PatientEvent event = PatientEvent.parseFrom(record.value());
//
//            log.info("🩺 Patient event consumed:");
//            log.info("➡️ ID: {}", event.getPatientId());
//            log.info("➡️ Name: {}", event.getName());
//            log.info("➡️ Email: {}", event.getEmail());
//            log.info("➡️ Event Type: {}", event.getEventType());
//
//        } catch (Exception e) {
//            log.error("❌ Failed to parse PatientEvent from Kafka: {}", e.getMessage(), e);
//        }
//    }
//}
