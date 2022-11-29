package com.internship.iotcontrollerkafka.repository;

import com.internship.iotcontrollerkafka.mapper.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
