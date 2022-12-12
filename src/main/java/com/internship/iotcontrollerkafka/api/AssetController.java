package com.internship.iotcontrollerkafka.api;

import com.internship.iotcontrollerkafka.dto.AssetDto;
import com.internship.iotcontrollerkafka.service.AssetService;
import com.internship.iotcontrollerkafka.service.DeviceMetricsService;
import com.internship.iotcontrollerkafka.service.IotSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
