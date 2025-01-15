package org.example.adapterout.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_access_log")
public class UrlAccessLogJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shortened_url_id", nullable = false)
    private UrlJpaEntity urlJpaEntity;

    @Column(name = "ip_address", nullable = false, length = 45)
    private String ipAddress;

    @Column(name = "user_agent", length = 255)
    private String userAgent;

    @Column(name = "accessed_at", nullable = false)
    private LocalDateTime accessedAt;
}
