package org.example.adapterout.repository;

import org.example.adapterout.entity.UrlJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlJpaEntity, Long> {
    boolean existsByShortKey(String shortKey);

    Optional<UrlJpaEntity> findByShortKey(String shortKey);
}
