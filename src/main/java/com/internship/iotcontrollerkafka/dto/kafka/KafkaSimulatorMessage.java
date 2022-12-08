package com.internship.iotcontrollerkafka.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaSimulatorMessage implements Serializable {

    private Long deviceId;
    private Long machineId;
    private Long sessionId;

}
