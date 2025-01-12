package org.example.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.Url;
import org.example.core.port.UrlRepositoryPort;
import org.example.persistence.entity.UrlEntity;
import org.example.persistence.repository.UrlRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UrlRepositoryAdapter implements UrlRepositoryPort {
    private final UrlRepository urlRepository;

    @Override
    public void save(Url url) {
        UrlEntity entity = UrlEntity.fromDomain(url); // 도메인 → 엔터티 변환
        urlRepository.save(entity);
    }

    @Override
    public Optional<Url> findByShortKey(String shortKey) {
        return urlRepository.findByShortKey(shortKey)
                .map(UrlEntity::toDomain); // 엔터티 → 도메인 변환
    }
}
