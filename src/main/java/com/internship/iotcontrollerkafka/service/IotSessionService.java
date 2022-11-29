package com.internship.iotcontrollerkafka.service;

import com.internship.iotcontrollerkafka.dto.AssetDto;
import com.internship.iotcontrollerkafka.mapper.AssetMapper;
import com.internship.iotcontrollerkafka.mapper.entity.IotSession;
import com.internship.iotcontrollerkafka.repository.IotSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IotSessionService {

    @Autowired
    private IotSessionRepository iotSessionRepository;

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetMapper assetMapper;

    public Long startSession(Long machineId, Long deviceId) throws Exception {
        IotSession iotSession = new IotSession();
        AssetDto machine = assetService.findById(machineId);
        iotSession.setMachine(assetMapper.toEntity(machine));
        AssetDto device = assetService.findById(deviceId);
        iotSession.setDevice(assetMapper.toEntity(device));
        iotSession.setStartTime(LocalDateTime.now());
        return iotSessionRepository.save(iotSession).getId();
    }

    public void endSession(Long id) throws Exception {
        IotSession iotSession = iotSessionRepository.findById(id).orElseThrow(() -> new Exception("IotSession#" + id + " not found!"));
        iotSession.setEndTime(LocalDateTime.now());
        iotSessionRepository.save(iotSession);
    }

    public IotSession findById(Long id) throws Exception {
        IotSession iotSession = iotSessionRepository.findById(id).orElseThrow(() -> new Exception("IotSession#" + id + " not found!"));
        return iotSession;
    }
}
