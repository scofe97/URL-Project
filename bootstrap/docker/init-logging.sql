-- init-logging.sql
ALTER SYSTEM SET log_destination = 'csvlog';
ALTER SYSTEM SET logging_collector = on;
ALTER SYSTEM SET log_directory = '/var/lib/postgresql/log';
ALTER SYSTEM SET log_filename = 'postgresql-%Y-%m-%d_%H%M%S.log';
ALTER SYSTEM SET log_rotation_age = '1d';
ALTER SYSTEM SET log_rotation_size = '100MB';

-- 슬로우 쿼리 로깅
ALTER SYSTEM SET log_min_duration_statement = '1000';  -- 1초 이상 걸리는 쿼리
ALTER SYSTEM SET log_statement = 'all';                -- 모든 SQL문 로깅

-- 기본 문제 해결 로깅
ALTER SYSTEM SET log_checkpoints = on;
ALTER SYSTEM SET log_connections = on;
ALTER SYSTEM SET log_disconnections = on;
ALTER SYSTEM SET log_lock_waits = on;                 -- 락 대기 로깅
ALTER SYSTEM SET log_temp_files = 0;                  -- 임시 파일 사용 로깅

-- 에러 로깅 설정
ALTER SYSTEM SET log_min_messages = 'warning';

-- auto_explain 설정
ALTER SYSTEM SET shared_preload_libraries = 'auto_explain';
ALTER SYSTEM SET auto_explain.log_min_duration = '1000';  -- 1초 이상 걸린 쿼리의 실행계획 로깅
ALTER SYSTEM SET auto_explain.log_analyze = on;           -- 실제 실행 시간과 행 개수 포함
ALTER SYSTEM SET auto_explain.log_verbose = on;           -- 더 자세한 정보 포함
ALTER SYSTEM SET auto_explain.log_buffers = on;           -- 버퍼 사용량 포함
ALTER SYSTEM SET auto_explain.log_timing = on;            -- 각 노드의 실행 시간 포함

CREATE EXTENSION IF NOT EXISTS auto_explain;