# (1) 스테이지 1: 빌드 Stage
# 1-1) 빌드 도구(Gradle 7.5 + JDK 17) 이미지 사용
FROM gradle:8.11.1-jdk17 AS builder
# 1-2) 작업 디렉토리 지정
WORKDIR /app

# 1-3) 소스코드를 컨테이너 내부로 복사
COPY . /app

# 1-4) Gradle 빌드 명령 실행
RUN gradle clean build --no-daemon

# (2) 스테이지 2: 실행 Stage
# 2-1) OpenJDK 17 JDK 버전이 들어 있으며, -slim 태그를 통해 불필요한 리눅스 패키지를 줄인 버전
FROM openjdk:17-jdk-slim
WORKDIR /app
# 2-2) 첫 번째 스테이지에서 빌드된 jar 파일을 복사
COPY --from=builder /app/build/libs/local_test-0.0.1-SNAPSHOT.jar app.jar

# 2-3) 컨테이너 시작 시 실행될 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
