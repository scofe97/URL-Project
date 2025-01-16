package org.example.practice.adapterout.repository;

import org.example.practice.adapterout.entity.UrlAccessLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlAccessLogRepository extends JpaRepository<UrlAccessLogJpaEntity, Long> {


}
