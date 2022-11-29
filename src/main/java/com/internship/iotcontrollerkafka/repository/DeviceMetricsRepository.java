package com.internship.iotcontrollerkafka.repository;

import com.internship.iotcontrollerkafka.mapper.entity.DeviceMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceMetricsRepository extends JpaRepository<DeviceMetrics, Long> {
}
