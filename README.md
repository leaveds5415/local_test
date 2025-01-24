## 사용법
### 시작
1. `docker compose up -d`
2. 인텔리제이에서 서버 로컬 실행
3. 컨테이너 확인
    - 그라파나: http://localhost:3000/
    - 프로메테우스: http://localhost:9090/
        - status > target health 확인

### 종료
- 서버만 종료하면 됩니다.
  - 단, `ddl-auto: create` 때문에 서버 구동 시 db 는 초기화됩니다.  
- `docker compose down` 을 사용하면 컨테이너가 종료됩니다. 컨테이너 재구동시 데이터를 보존합니다.
- `docker compose down --volumes` 은 볼륨까지 삭제합니다. 

## ⌘ 메모
### 컨테이너 별로 리소스 잘 제한하고 있을까?
- `docker stats` 로 확인
    ```text
    예시)
    
    CONTAINER ID   NAME           CPU %     MEM USAGE / LIMIT   MEM %     NET I/O           BLOCK I/O       PIDS
    05dcac5ed2f2   prometheus     0.00%     42.03MiB / 512MiB   8.21%     12.4MB / 5.97MB   0B / 950kB      9
    fae2db699852   mysql-db       0.74%     221.7MiB / 256MiB   86.60%    35.6MB / 16.5MB   146MB / 513MB   46
    6a1b8a214b53   redis-server   0.18%     12.59MiB / 256MiB   4.92%     13.8kB / 14.9kB   0B / 0B         5
    84865022095f   grafana        0.05%     129.1MiB / 512MiB   25.21%    17.3MB / 28MB     0B / 438kB      19
    ```
- cpu 는 0.1 주면 10%, 2 주면 200% 사용한다. 로컬 머신 코어 수에 따라서 달라짐.
- 메모리도 잘 제한됨.


### mysql 컨테이너 메모리 할당 테스트 
- 128m: 컨테이너가 뜨질 못한다.
- 256m: 컨네이너가 뜨긴함. 
  - 아무것도 안해도 메모리 사용률이 90% 에 가까움.
  - 가장 처음 도커 컴포즈 실행해서 컨테이너 볼륨 설정하는 시기에 뭔가 작업이 있는지 서버 연결이 안된다.
    - 1~2분 기다리면 됨;;
- 512m: 빠르게 뜬다.
  - 여전히 처음 서버 연결이 안될 때가 있다. 근데 체감상 훨씬 짧음.
