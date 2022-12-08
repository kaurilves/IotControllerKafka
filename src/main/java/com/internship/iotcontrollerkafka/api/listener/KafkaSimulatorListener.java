package com.internship.iotcontrollerkafka.api.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.iotcontrollerkafka.config.KafkaTopicConfig;
import com.internship.iotcontrollerkafka.dto.kafka.KafkaSimulatorMessage;
import com.internship.iotcontrollerkafka.service.IotSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSimulatorListener {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private IotSessionService iotSessionService;

    @KafkaListener(topics= KafkaTopicConfig.START_SESSION)
    public void listenStartSessions(@Payload String message) throws Exception {
        var simulatorMessage = mapper.readValue(message, KafkaSimulatorMessage.class);
        iotSessionService.startSession(simulatorMessage.getMachineId(), simulatorMessage.getDeviceId());
        log.info("LISTENER: GOT MESSAGE {}", simulatorMessage);
    }

    @KafkaListener(topics= KafkaTopicConfig.END_SESSION)
    public void listenEndSession(@Payload String message) throws Exception {
        log.info("LISTENER END SESSION: GOT MESSAGE {}", message);
    }

    @KafkaListener(topics= KafkaTopicConfig.METRICS_TOPIC)
    public void listenSendMetrics(@Payload String message) throws Exception {
        log.info("LISTENER: GOT MESSAGE {}", message);
    }


}
