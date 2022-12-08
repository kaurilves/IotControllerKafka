package com.internship.iotcontrollerkafka.service;


import com.internship.iotcontrollerkafka.dto.DeviceMetricsDto;
import com.internship.iotcontrollerkafka.mapper.DeviceMetricsMapper;
import com.internship.iotcontrollerkafka.entity.DeviceMetrics;
import com.internship.iotcontrollerkafka.entity.IotSession;
import com.internship.iotcontrollerkafka.repository.DeviceMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceMetricsService {

    @Autowired
    private DeviceMetricsRepository deviceMetricsRepository;

    @Autowired
    private DeviceMetricsMapper deviceMetricsMapper;

    @Autowired
    private IotSessionService iotSessionService;

    public void save(Long id, DeviceMetricsDto deviceMetricsDto) throws Exception {
        DeviceMetrics deviceMetrics = deviceMetricsMapper.toEntity(deviceMetricsDto);
        IotSession iotSession = iotSessionService.findById(id);
        deviceMetrics.setIotSession(iotSession);
        deviceMetricsRepository.save(deviceMetrics);
    }
}

