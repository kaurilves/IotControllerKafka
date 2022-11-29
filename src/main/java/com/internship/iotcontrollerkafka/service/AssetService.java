package com.internship.iotcontrollerkafka.service;

import com.internship.iotcontrollerkafka.dto.AssetDto;
import com.internship.iotcontrollerkafka.mapper.AssetMapper;
import com.internship.iotcontrollerkafka.mapper.entity.Asset;
import com.internship.iotcontrollerkafka.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetRepository assetRepository;

    public AssetDto save(AssetDto assetDto) {
        Asset asset = assetMapper.toEntity(assetDto);
        assetRepository.save(asset);
        return assetMapper.toDto(asset);
    }

    public AssetDto findById(Long id) throws Exception {
        Asset asset = assetRepository.findById(id).orElseThrow(() -> new Exception("Asset#" + id + " not found!"));
        return assetMapper.toDto(asset);
    }

    public List<AssetDto> findAll() {
        List<Asset> assets = assetRepository.findAll();
        return assetMapper.toDtoList(assets);
    }

    public AssetDto update(AssetDto assetDto) throws Exception {
        Asset asset = assetRepository.findById(assetDto.getId()).orElseThrow(() -> new Exception("Asset#" + assetDto.getId() + " not found!"));
        assetMapper.update(asset, assetDto);
        assetRepository.save(asset);
        return assetMapper.toDto(asset);
    }

    public void delete(Long id) {
        assetRepository.deleteById(id);
    }
}
