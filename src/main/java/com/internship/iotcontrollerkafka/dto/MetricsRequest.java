package com.internship.iotcontrollerkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MetricsRequest implements Serializable {

    private Integer value;
    private LocalDateTime timeStamp;

}
