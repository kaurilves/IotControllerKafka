package com.internship.iotcontrollerkafka.api.kafka;

import com.internship.iotcontrollerkafka.config.KafkaListenerConfig;
import com.internship.iotcontrollerkafka.config.KafkaTopicConfig;
import com.internship.iotcontrollerkafka.dto.DeviceConnectionResponse;
import com.internship.iotcontrollerkafka.dto.DeviceConnectionRequest;
import com.internship.iotcontrollerkafka.dto.MetricsRequest;
import com.internship.iotcontrollerkafka.service.DeviceMetricsService;
import com.internship.iotcontrollerkafka.service.IotSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaController {

    @Autowired
    private IotSessionService iotSessionService;

    @Autowired
    private DeviceMetricsService deviceMetricsService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaListenerConfig kafkaListenerConfig;


    @KafkaListener(topics = KafkaTopicConfig.START_SESSION, groupId = "myGroup", containerFactory = "factory")
    @SendTo(KafkaTopicConfig.RESPONSE)
    public DeviceConnectionResponse listenStartSessions(
            DeviceConnectionRequest deviceConnectionRequest
    ) throws Exception {
        log.info("CONSUMER: Got message to start IOT session between  machine#{} & device#{}",
                deviceConnectionRequest.getMachineId(), deviceConnectionRequest.getMachineId());
        return iotSessionService.startSession(deviceConnectionRequest);
    }

    @KafkaListener(topics = KafkaTopicConfig.END_SESSION, groupId = "myGroup", containerFactory = "factory")
    public void listenEndSession(Long sessionId) throws Exception {
        log.info("CONSUMER: Got message to end IOT session:#{}!", sessionId);
        iotSessionService.endSession(sessionId);
    }

    @KafkaListener(topics = KafkaTopicConfig.METRICS_TOPIC, groupId = "myGroup", containerFactory = "factory")
    public void listenSendMetrics(
            MetricsRequest metricsRequest,
            @Header(KafkaTopicConfig.SESSION_ID) Long sessionId
    ) throws Exception {
        log.info("CONSUMER: Got metrics {} for IOT session#{}", metricsRequest, sessionId);
        deviceMetricsService.save(metricsRequest, sessionId);
    }
}
