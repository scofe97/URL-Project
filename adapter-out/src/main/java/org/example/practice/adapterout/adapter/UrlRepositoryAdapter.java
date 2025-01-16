package org.example.practice.adapterout.adapter;

import lombok.RequiredArgsConstructor;
import org.example.practice.adapterout.entity.UrlJpaEntity;
import org.example.practice.adapterout.repository.UrlRepository;
import org.example.practice.application.domain.model.Url;
import org.example.practice.application.port.out.UrlRepositoryPort;
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
    public boolean existsByShortenedUrl(String shortenUrl) {
        return urlRepository.existsByShortenedUrl(shortenUrl);
    }

    @Override
    public Optional<Url> findByShortenedUrl(String shortKey) {
        return urlRepository.findByShortenedUrl(shortKey)
                .map(UrlJpaEntity::toDomain); // 엔터티 → 도메인 변환
    }
}
