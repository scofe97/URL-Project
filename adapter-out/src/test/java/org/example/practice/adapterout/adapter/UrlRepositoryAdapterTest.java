package org.example.practice.adapterout.adapter;

import org.example.practice.adapterout.config.TestJpaConfig;
import org.example.practice.adapterout.entity.UrlJpaEntity;
import org.example.practice.adapterout.repository.UrlRepository;
import org.example.practice.application.domain.model.Url;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = {TestJpaConfig.class})
class UrlRepositoryAdapterTest {

    @Autowired
    private UrlRepositoryAdapter urlRepositoryAdapter;

    @Autowired
    private UrlRepository urlRepository;


    @Test
    void save() {
        // given: 테스트에 사용할 데이터 준비
        Url url = Url.builder()
                .shortenedUrl("short1")
                .originalUrl("http://example.com")
                .createdAt(LocalDateTime.now())
                .clickCount(0)
                .build();

        urlRepositoryAdapter.save(url);

        Optional<UrlJpaEntity> foundEntity = urlRepository.findByShortenedUrl("short1");
        assertThat(foundEntity.isPresent()).isTrue();
        assertThat(foundEntity.get().getOriginalUrl()).isEqualTo("http://example.com");
        assertThat(foundEntity.get().getShortenedUrl()).isEqualTo("short1");
    }

    @Test
    void existsByShortenedUrl() {
        Url url = Url.builder()
                .shortenedUrl("short1")
                .originalUrl("http://example.com")
                .createdAt(LocalDateTime.now())
                .clickCount(0)
                .build();
        urlRepositoryAdapter.save(url);

        boolean exists = urlRepositoryAdapter.existsByShortenedUrl("short1");
        boolean notExists = urlRepositoryAdapter.existsByShortenedUrl("short2");

        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    void findByShortenedUrl() {
        Url url = Url.builder()
                .shortenedUrl("short1")
                .originalUrl("http://example.com")
                .createdAt(LocalDateTime.now())
                .clickCount(0)
                .build();
        urlRepositoryAdapter.save(url);

        // when
        Optional<Url> found = urlRepositoryAdapter.findByShortenedUrl("short1");
        Optional<Url> notFound = urlRepositoryAdapter.findByShortenedUrl("short2");

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getShortenedUrl()).isEqualTo("short1");
        assertThat(found.get().getOriginalUrl()).isEqualTo("http://example.com");
        assertThat(notFound).isEmpty();
    }
}