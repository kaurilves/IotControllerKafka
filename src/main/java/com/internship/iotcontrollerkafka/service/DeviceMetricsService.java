package com.internship.iotcontrollerkafka.service;


import com.internship.iotcontrollerkafka.dto.MetricsRequest;
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

    public void save(MetricsRequest metricsRequest, Long sessionId) throws Exception {
        DeviceMetrics deviceMetrics = deviceMetricsMapper.toEntity(metricsRequest);
        deviceMetrics.setTimeStamp(metricsRequest.getTimeStamp());
        IotSession iotSession = iotSessionService.findById(sessionId);
        if (iotSession.getEndTime() == null) {
            deviceMetrics.setIotSession(iotSession);
            deviceMetricsRepository.save(deviceMetrics);
        }
    }
}

