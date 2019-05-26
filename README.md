# springboot-jwt-java

- Springboot2
- Java-8
- Gradle
- Jwt
- JPA
- Postgresql

## Quick Start

### Rename application.yml (application.yml 파일 이름 수정)

```bash
$> cd src/main/resources
$> mv application.ex.yml ./application.yml
```

### Edit application.yml (파일 내용 수정)

```text
spring:
    ...
    url: jdbc:postgresql://localhost:5432/<DB name>
    username: <DB username>
    password: <DB password>
    ...
```

### Server run

```bash
$> cd <project root>
$> ./gradlew bootRun --console=plain
```

### Request http

[Post] sign-up

```text
POST http://localhost:8080/auth/sign-up
content-type: application/json

{
    "userEmail": "test@sc372.io",
    "password": "test"
}
```

[Get] sign-in

```text
POST http://localhost:8080/auth/sign-in
content-type: application/json

{
    "userEmail": "test@sc372.io",
    "password": "test"
}
```

### Todo

- [x] Jwt init
- [ ] test code
- [ ] user CRUD
- [x] uer role table foreign key
- [ ] docker init