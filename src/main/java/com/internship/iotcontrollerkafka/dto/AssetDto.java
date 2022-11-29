package com.internship.iotcontrollerkafka.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssetDto {

    private Long id;
    private String name;
    private String type;

}
