package com.internship.iotcontrollerkafka.service;

import com.internship.iotcontrollerkafka.dto.AssetDto;
import com.internship.iotcontrollerkafka.dto.DeviceConnectionRequest;
import com.internship.iotcontrollerkafka.dto.DeviceConnectionResponse;
import com.internship.iotcontrollerkafka.entity.IotSession;
import com.internship.iotcontrollerkafka.mapper.AssetMapper;
import com.internship.iotcontrollerkafka.mapper.IotSessionMapper;
import com.internship.iotcontrollerkafka.repository.IotSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class IotSessionService {

    @Autowired
    private IotSessionRepository iotSessionRepository;

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private IotSessionMapper iotSessionMapper;

    public DeviceConnectionResponse startSession(DeviceConnectionRequest deviceConnectionRequest) throws Exception {
        IotSession iotSession = new IotSession();
        AssetDto machine = assetService.findById(deviceConnectionRequest.getMachineId());
        iotSession.setMachine(assetMapper.toEntity(machine));
        AssetDto device = assetService.findById(deviceConnectionRequest.getDeviceId());
        iotSession.setDevice(assetMapper.toEntity(device));
        iotSession.setStartTime(LocalDateTime.now());
        iotSessionRepository.save(iotSession);
        return iotSessionMapper.toDto(iotSession);

    }

    public void endSession(Long sessionId) throws Exception {
        IotSession iotSession = findById(sessionId);
        iotSession.setEndTime(LocalDateTime.now());
        iotSessionRepository.save(iotSession);
    }

    public IotSession findById(Long sessionId) throws Exception {
        if (iotSessionRepository.findById(sessionId).isEmpty()) {
            throw new Exception("Session#" + sessionId + " not found!");
        }
        return iotSessionRepository.findById(sessionId).get();
    }
}

