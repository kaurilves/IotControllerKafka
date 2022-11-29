package com.internship.iotcontrollerkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IotSessionDto implements Serializable {

    private Long sessionId;

}

