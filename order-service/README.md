# Order Service

Microservice projesi: **order-service**
Spring Boot + Spring Cloud + Eureka Client + Config Server + JPA (MySQL) + MapStruct + Resilience4j + OpenFeign kullanıyor.

---

## 📦 Proje Yapısı

```
order-service/
├── src/
│   └── main/
│       ├── java/com/eneskumas/orderservice/
│       │   ├── OrderServiceApplication.java
│       │   ├── controller/OrderController.java
│       │   ├── dto/
│       │   │   ├── OrderCreateRequestDto.java
│       │   │   ├── OrderResponseDto.java
│       │   │   └── OrderUpdateRequestDto.java
│       │   ├── service
│       │   │   ├── CustomerService.java
│       │   │   ├── OrderService.java
│       │   │   └── /impl/OrderServiceImpl.java
│       │   ├── mapper/OrderMapper.java
│       │   ├── repository/OrderRepository.java
│       │   ├── entity/Order.java
│       │   ├── client/
│       │   │   ├── CustomerClient.java
│       │   │   ├── CustomerClientConfig.java
│       │   │   └── FeignCustomerErrorDecoder.java
│       │   ├── exception/
│       │   │   ├── OrderNotFoundException.java
│       │   │   ├── CustomerNotFoundException.java
│       │   │   ├── CustomerServiceException.java
│       │   │   └── GlobalExceptionHandler.java
│       │   └── model/
│       │       ├── Customer.java
│       │       └── ExceptionMessage.java
│       └── resources/
│           └── application.yml
├── pom.xml
├── Dockerfile
└── README.md
```

---

## ⚙️ Konfigürasyon

**application.yml** içeriği:

```yaml
spring:
  application:
    name: order-service
  config:
    import: optional:configserver:${CONFIG_SERVER_URL:http://localhost:8888}
    failFast: true
    retry:
      maxAttempts: 6
      initialInterval: 2000
      multiplier: 1.5
  profiles:
    active: dev

resilience4j:
  circuitbreaker:
    instances:
      customerServiceCB:
        register-health-indicator: true
        sliding-window-type: COUNT_BASED
        sliding-window-size: 5
        minimum-number-of-calls: 5
        failure-rate-threshold: 50
        wait-duration-in-open-state:
          seconds: 10
        permitted-number-of-calls-in-half-open-state: 2
        automatic-transition-from-open-to-half-open-enabled: true

management:
  endpoints:
    web:
      exposure:
        include: refresh
  health:
    circuitbreakers:
      enabled: true
logging:
  level:
    io.github.resilience4j.circuitbreaker: DEBUG
```

**Config Server’deki order-service dosyaları:**

* **order-service.yml** (default)
* **order-service-dev.yml**
* **order-service-prod.yml**

Ortam bazlı veritabanı ve global mesaj ayarlarını yönetmek için kullanılır.

---

## ▶️ Çalıştırma

### 1. Maven ile

```bash
mvn spring-boot:run
```

### 2. Jar ile

```bash
mvn clean package -DskipTests
java -jar target/order-service-0.0.1-SNAPSHOT.jar
```

### 3. Docker ile

```bash
mvn clean package -DskipTests
docker build -t order-service .
docker run -p 8084:8084 order-service
```

---

## 🗄️ Database

* MySQL kullanılıyor.
* Dev ortamı: `jdbc:mysql://localhost:3306/orders`
* Prod ortamı: `jdbc:mysql://localhost:3306/orders_prod`
* JPA Hibernate `ddl-auto: update` ile tablo otomatik güncelleniyor (dev ve prod ayrı).

---

## 🖥️ API Endpoints

| HTTP Method | Endpoint                    | Açıklama                           |
| ----------- | --------------------------- | ---------------------------------- |
| GET         | `/api/orders`               | Tüm siparişleri getirir            |
| GET         | `/api/orders/{id}`          | ID ile sipariş getirir             |
| GET         | `/api/orders/customer/{id}` | Müşteri ID ile siparişleri getirir |
| POST        | `/api/orders`               | Yeni sipariş oluşturur             |
| PATCH       | `/api/orders/{id}`          | Siparişi günceller                 |
| DELETE      | `/api/orders/{id}`          | Siparişi siler                     |

---

## 📌 Örnek JSON

**POST /api/orders**

```json
{
  "customerId": 1,
  "name": "Laptop",
  "price": 8500.00,
  "quantity": 2
}
```

**Response**

```json
{
  "id": 1,
  "customerId": 1,
  "name": "Laptop",
  "price": 8500.00,
  "quantity": 2
}
```

---

## 🔧 Geliştirme Notları

* MapStruct ile entity ↔ DTO dönüşümleri yapılmaktadır.
* Feign Client ile **Customer Service** çağrısı yapılmaktadır.
* Resilience4j CircuitBreaker ile customer service fallback yönetimi uygulanmaktadır.
* GlobalExceptionHandler ile tüm özel ve genel exception’lar yönetiliyor.
* Config Server ile ortam bazlı konfigürasyon sağlanıyor.
* Spring Boot Actuator `/actuator/refresh` endpoint’i ile konfigürasyon canlı yenilenebilir.
* Lombok kullanılarak boilerplate kod azaltıldı.

---

## 👨‍💻 Geliştirici

* [Enes Kumaş](https://github.com/eneskums)
