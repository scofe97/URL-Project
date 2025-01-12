package org.example.persistence.repository;

import org.example.persistence.entity.UrlAccessLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlAccessLogRepository extends JpaRepository<UrlAccessLogEntity, Long> {


}
