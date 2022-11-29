package com.internship.iotcontrollerkafka.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceMetricsDto implements Serializable {

    private Integer value;

    @Builder.Default
    private LocalDateTime timeStamp = LocalDateTime.now();

}
