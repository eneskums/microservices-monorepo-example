# Gateway Service

Bu servis, **Spring Cloud Gateway** kullanarak mikroservisler için API Gateway görevi görür.
Gateway, istekleri path bazlı yönlendirir ve Eureka Client ile servis keşfi sağlar.

---

## 🚀 Kullanılan Teknolojiler

* **Java 21**
* **Spring Boot 3**
* **Spring Cloud 2025.0.0**

    * Gateway
    * Eureka Server
    * Config Client
* **Actuator** (health & metrics)
* **Maven**
* **Docker**

---

## 📂 Proje Yapısı

```
gateway-service/
 ├── src/
 │   └── main/
 │       ├── java/com/eneskumas/gatewayservice/GatewayServiceApplication.java
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
  port: 8089

spring:
  application:
    name: gateway-service
  config:
    import: optional:configserver:${CONFIG_SERVER_URL:http://localhost:8888}
    failFast: true
    retry:
      maxAttempts: 6
      initialInterval: 2000
      multiplier: 1.5
  profiles:
    active: dev
  cloud:
    gateway:
      server:
        webflux:
          routes:
            - id: order-service-route
              uri: lb://order-service
              predicates:
                - Path=/api/orders/**
            - id: customer-service-route
              uri: lb://customer-service
              predicates:
                - Path=/api/customers/**

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${DISCOVERY_SERVICE_URL:http://localhost:8761/eureka/}
```

📌 Not:
Eğer Eureka Server veya Config Server farklı bir adreste ise `CONFIG_SERVER_URL` ve `DISCOVERY_SERVICE_URL` alanları değiştirilmelidir.

---

## ▶️ Çalıştırma

### 1. Maven ile

```bash
mvn spring-boot:run
```

### 2. Jar ile

```bash
mvn clean package -DskipTests
java -jar target/gateway-service-0.0.1-SNAPSHOT.jar
```

### 3. Docker ile

```bash
mvn clean package -DskipTests
docker build -t gateway-service .
docker run -p 8089:8089 gateway-service
```

---

## 🌐 Servis Adresleri

* **Gateway Service** → [http://localhost:8089](http://localhost:8089)
* **Actuator** → [http://localhost:8089/actuator](http://localhost:8089/actuator)

---

## 📌 Geliştirme Notları

* Spring Cloud Gateway ile API Gateway yapılandırması yapılmıştır.
* Eureka Client ile servisler otomatik keşfedilir.
* Config Server üzerinden ortam bazlı konfigürasyon yönetimi uygulanır.
* WebFlux kullanarak reactive gateway route handling sağlanır.
* Tüm istekler path bazlı servis yönlendirmesi ile ilgili mikroservislere iletilir.
* Spring Boot Actuator endpoint’leri ile health check ve konfigürasyon yenileme yapılabilir.

---

## 👨‍💻 Geliştirici

* [Enes Kumaş](https://github.com/eneskums)