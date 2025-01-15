package org.example.adapterout.adapter;

import lombok.RequiredArgsConstructor;
import org.example.adapterout.entity.UrlJpaEntity;
import org.example.adapterout.repository.UrlRepository;
import org.example.application.domain.model.Url;
import org.example.application.port.out.UrlRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UrlRepositoryAdapter implements UrlRepositoryPort {
    private final UrlRepository urlRepository;

    @Override
    public void save(Url url) {
        UrlJpaEntity entity = UrlJpaEntity.fromDomain(url); // 도메인 → 엔터티 변환
        urlRepository.save(entity);
    }

    @Override
    public boolean existsByShortenKey(String shortenKey) {
        return urlRepository.existsByShortKey(shortenKey);
    }

    @Override
    public Optional<Url> findByShortKey(String shortKey) {
        return urlRepository.findByShortKey(shortKey)
                .map(UrlJpaEntity::toDomain); // 엔터티 → 도메인 변환
    }
}
