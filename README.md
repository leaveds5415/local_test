## 사용법
1. docker compose up -d
2. 인텔리제이에서 서버 로컬 실행
3. 컨테이너 확인
    - 그라파나: http://localhost:3000/
    - 프로메테우스: http://localhost:9090/
        - status > target health 확인

## 종료
- 서버만 종료하면 됩니다.
  - 단, `ddl-auto: create` 때문에 서버 구동 시 db 는 초기화됩니다.  
- `docker compose down` 을 사용하면 컨테이너가 종료됩니다. 컨테이너 재구동시 데이터를 보존합니다.
- `docker compose down --volumes` 은 볼륨까지 삭제합니다. 