package com.internship.iotcontrollerkafka.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device_metrics")
public class DeviceMetrics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iot_sessions_id", nullable = false)
    private IotSession iotSession;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timeStamp;

}

