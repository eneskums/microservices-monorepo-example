# Discovery Service

Bu servis, **Spring Cloud Eureka Server** kullanarak mikroservislerin keşif (service discovery) işlemlerini yönetir.
Mikroservisler bu Eureka server’a kayıt olur ve diğer servisler Eureka üzerinden birbirlerini bulabilir.

---

## 🚀 Kullanılan Teknolojiler

* **Java 21**
* **Spring Boot 3**
* **Spring Cloud 2025.0.0**

    * Eureka Server
    * Config Client
* **Actuator** (health & metrics)
* **Maven**
* **Docker**

---

## 📂 Proje Yapısı

```
discovery-service/
 ├── src/
 │   └── main/
 │       ├── java/com/eneskumas/discoveryservice/DiscoveryServiceApplication.java
 │       └── resources/application.yml
 ├── pom.xml
 ├── Dockerfile
 └── README.md
```

---

## ⚙️ Konfigürasyon

`application.yml` içeriği:

```yaml
server:
  port: 8761

spring:
  application:
    name: discovery-server
  config:
    import: optional:configserver:${CONFIG_SERVER_URL:http://localhost:8888}

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false

management:
  endpoints:
    web:
      exposure:
        include: health, info, refresh
  endpoint:
    health:
      show-details: always
```

📌 Not:
`CONFIG_SERVER_URL` environment variable ile config server adresi Docker veya CI/CD ortamına göre esnek bir şekilde ayarlanabilir.

---

## ▶️ Çalıştırma

### 1. Maven ile

```bash
mvn spring-boot:run
```

### 2. Jar ile

```bash
mvn clean package -DskipTests
java -jar target/discovery-service-0.0.1-SNAPSHOT.jar
```

### 3. Docker ile

```bash
mvn clean package -DskipTests
docker build -t discovery-service .
docker run -p 8761:8761 discovery-service
```

---

## 🌐 Servis Adresleri

* **Eureka Dashboard** → [http://localhost:8761](http://localhost:8761)
* **Actuator** → [http://localhost:8761/actuator](http://localhost:8761/actuator)

---

## 📌 Geliştirme Notları

* Config server import’u `CONFIG_SERVER_URL` ile esnek hale getirildi.
* Health check ve metrics actuator ile izlenebilir.

---

## 👨‍💻 Geliştirici

* [Enes Kumaş](https://github.com/eneskums)
