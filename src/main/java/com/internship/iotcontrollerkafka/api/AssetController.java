package com.internship.iotcontrollerkafka.api;

import com.internship.iotcontrollerkafka.dto.AssetDto;
import com.internship.iotcontrollerkafka.dto.DeviceMetricsDto;
import com.internship.iotcontrollerkafka.dto.IotSessionDto;
import com.internship.iotcontrollerkafka.service.AssetService;
import com.internship.iotcontrollerkafka.service.DeviceMetricsService;
import com.internship.iotcontrollerkafka.service.IotSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
@Slf4j
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private IotSessionService iotSessionService;

    @Autowired
    private DeviceMetricsService deviceMetricsService;


    @PostMapping
    public ResponseEntity<AssetDto> createAsset(AssetDto assetDto) throws Exception {
        if (assetDto == null) {
            throw new Exception("Asset data is missing");
        }
        return ResponseEntity
                .ok()
                .body(assetService.save(assetDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> getAssetById(@PathVariable Long id) throws Exception {
        AssetDto assetFound = assetService.findById(id);
        return ResponseEntity
                .ok()
                .body(assetFound);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AssetDto>> getAllAssets() {
        return ResponseEntity
                .ok()
                .body(assetService.findAll());
    }

    @PutMapping()
    public ResponseEntity<AssetDto> assetUpdate(@RequestBody AssetDto assetDto) throws Exception {
        if (assetDto == null) {
            throw new Exception("Asset data is missing");
        }
        return ResponseEntity
                .ok()
                .body(assetService.update(assetDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable Long id) throws Exception {
        assetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/iotsession/{machineId}/{deviceId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IotSessionDto> startIotSession(@PathVariable Long machineId, @PathVariable Long deviceId) throws Exception {
        log.info("Session between machine#{} & device#{} started!", machineId, deviceId);
        return ResponseEntity.ok(new IotSessionDto( iotSessionService.startSession(machineId, deviceId)));
    }

    @PostMapping("/iotsession/{sessionId}")
    public ResponseEntity<?> endIotSession(@PathVariable Long sessionId) throws Exception {
        log.info("Session#{} ended", sessionId);
        iotSessionService.endSession(sessionId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/iotsession/{sessionId}/device_metrics")
    public ResponseEntity<?> saveDeviceMetrics (@PathVariable Long sessionId,  @RequestBody DeviceMetricsDto deviceMetricsDto) throws Exception {
        log.info("Got some metrics for session#{} - value: {} timestamp: {}", sessionId,  deviceMetricsDto.getValue(), deviceMetricsDto.getTimeStamp());
        deviceMetricsService.save(sessionId, deviceMetricsDto);
        return ResponseEntity.noContent().build();
    }

}
