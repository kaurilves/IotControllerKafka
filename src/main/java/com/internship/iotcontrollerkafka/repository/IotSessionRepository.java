package com.internship.iotcontrollerkafka.repository;

import com.internship.iotcontrollerkafka.mapper.entity.IotSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotSessionRepository extends JpaRepository<IotSession, Long> {
}
