package org.example.adapterout.repository;

import org.example.adapterout.entity.UrlAccessLogJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlAccessLogRepository extends JpaRepository<UrlAccessLogJpaEntity, Long> {


}
