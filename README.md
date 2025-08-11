# 목표
<ol>
  <li>Http Only 쿠키로 액세스 토큰 기반 인증 구현</li>
  <li>Redis를 사용한 리프레시 토큰 구현</li>
  <li>인증이 연동된 회원관리와 게시판 구현</li>
  <li>응답바디와 전역 예외처리로 획일화된 응답 구현</li>
</ol>

---

## 🧰 기술 스택

### Frontend
<p>
  <img src="https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white" />
  <img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB" />
</p>

### Backend
<p>
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring%20Boot%203-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" />
</p>

### Database
<p>
  <img src="https://img.shields.io/badge/MySQL%208-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white" />
</p>

### Auth
<p>
  <img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" />
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white" />
  <img src="https://img.shields.io/badge/Bcrypt-3385FF?style=for-the-badge&logoColor=white" />
</p>

### Build
<p>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" />
</p>

---

## 프로젝트 구조
```
jwt-board-springboot/
│
├── README.md
├── .gitignore
│
├── frontend/
│   ├── public/
│   │   ├── favicon.ico
│   │   ├── index.html
│   │   └── manifest.json
│   ├── src/
│   │   ├── index.tsx
│   │   ├── App.tsx
│   │   ├── pages/
│   │   ├── components/
│   │   ├── routes/
│   │   ├── api/
│   │   ├── store/
│   │   └── styles/
│   ├── .env
│   └── tsconfig.json
│
└── backend/
    ├── gradlew
    ├── build.gradle
    ├── settings.gradle
    ├── gradle/
    │   └── wrapper/
    │       ├── gradle-wrapper.jar
    │       └── gradle-wrapper.properties
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/
        │   │       └── example/
        │   │           └── demo/
        │   │               ├── config/
        │   │               ├── controller/
        │   │               ├── service/
        │   │               ├── Repository/
        │   │               ├── entity/
        │   │               ├── dto/
        │   │               ├── response/
        │   │               ├── security/
        │   │               ├── exception/
        │   │               └── util/
        │   │                   ├── AccessTokenCookie.java
        │   │                   └── RefreshTokenCookie.java
        │   └── resources/
        │       ├── application.properties
        │       └── secret.properties
        └── test/

```

---

# 요청과 응답

