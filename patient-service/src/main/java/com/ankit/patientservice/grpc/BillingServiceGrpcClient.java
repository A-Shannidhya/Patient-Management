/*
 * Copyright (c) 2025 Ayshi Shannidhya Panda. All rights reserved.
 *
 * This source code is confidential and intended solely for internal use.
 * Unauthorized copying, modification, distribution, or disclosure of this
 * file, via any medium, is strictly prohibited.
 *
 * Project: Patient Management
 * Author: Ayshi Shannidhya Panda
 * Created on: 2025-6-11
 */

package com.ankit.patientservice.grpc;

import com.ankit.billing.BillingRequest;
import com.ankit.billing.BillingResponse;
import com.ankit.billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Service
public class BillingServiceGrpcClient {

    @Value("${billing.service.address:localhost}")
    private String serverAddress;

    @Value("${billing.service.port:9001}")
    private int serverPort;

    private ManagedChannel channel;
    private BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub;

    @PostConstruct
    public void init() {
        try {
            log.info("Connecting to Billing Service at {}:{}", serverAddress, serverPort);
            this.channel = ManagedChannelBuilder
                    .forAddress(serverAddress, serverPort)
                    .usePlaintext()
                    .build();
            this.billingServiceBlockingStub = BillingServiceGrpc.newBlockingStub(channel);
            log.info("Successfully connected to Billing gRPC service.");
        } catch (Exception e) {
            log.error("Failed to create gRPC channel to BillingService: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize BillingServiceGrpcClient", e);
        }
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email) {
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();

        log.info("Sending gRPC request to Billing Service: {}", request);

        BillingResponse response = billingServiceBlockingStub.createBillingAccount(request);

        log.info("Received gRPC response from Billing Service: {}", response);
        return response;
    }

    @PreDestroy
    public void shutdown() {
        if (channel != null && !channel.isShutdown()) {
            log.info("Shutting down gRPC channel to Billing Service...");
            channel.shutdown();
        }
    }
}
