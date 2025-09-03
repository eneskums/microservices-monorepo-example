# Customer Service

Microservice projesi: **customer-service**

Spring Boot + Spring Cloud + Eureka Client + Config Server + JPA (MySQL) + MapStruct kullanıyor.

---

## 📦 Proje Yapısı

```
customer-service/
├── src/
│   └── main/
│       ├── java/com/eneskumas/customerservice/
│       │   ├── CustomerServiceApplication.java
│       │   ├── controller/CustomerController.java
│       │   ├── dto/
│       │       ├── CustomerCreateDto.java 
│       │       ├── CustomerResponseDto.java 
│       │       └── CustomerUpdateDto.java 
│       │   ├── service/CustomerService.java
│       │   ├── service/impl/CustomerServiceImpl.java
│       │   ├── mapper/CustomerMapper.java
│       │   ├── repository/CustomerRepository.java
│       │   ├── entity/Customer.java
│       │   └── exception/
│       │       ├── CustomerAlreadyExistsException.java 
│       │       ├── CustomerNotFoundException.java 
│       │       └── GlobalExceptionHandler.java 
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
    name: customer-service
  config:
    import: optional:configserver:${CONFIG_SERVER_URL:http://localhost:8888}
    failFast: true
    retry:
      maxAttempts: 6
      initialInterval: 2000
      multiplier: 1.5

  profiles:
    active: dev

management:
  endpoints:
    web:
      exposure:
        include: refresh
```

**Config Server’deki customer-service dosyaları:**

* **customer-service.yml** (default)
* **customer-service-dev.yml**
* **customer-service-prod.yml**

Bu dosyalar veritabanı ve global mesaj ayarlarını ortam bazlı yönetmek için kullanılır.

---

## ▶️ Çalıştırma

### 1. Maven ile

```bash
mvn spring-boot:run
```

### 2. Jar ile

```bash
mvn clean package -DskipTests
java -jar target/customer-service-0.0.1-SNAPSHOT.jar
```

### 3. Docker ile

```bash
mvn clean package -DskipTests
docker build -t customer-service .
docker run -p 8082:8082 customer-service
```

---

## 🗄️ Database

* MySQL kullanılıyor.
* Dev ortamı: `jdbc:mysql://localhost:3306/customers`
* Prod ortamı: `jdbc:mysql://localhost:3306/customers_prod`
* JPA Hibernate `ddl-auto: update` ile tablo otomatik güncelleniyor (dev ve prod ayrı).

---

## 🖥️ API Endpoints

| HTTP Method | Endpoint              | Açıklama                      |
| ----------- | --------------------- | ----------------------------- |
| GET         | `/api/customers`      | Tüm müşterileri getirir       |
| GET         | `/api/customers/{id}` | ID ile müşteri getirir        |
| POST        | `/api/customers`      | Yeni müşteri oluşturur        |
| PATCH       | `/api/customers/{id}` | Müşteri bilgilerini günceller |
| DELETE      | `/api/customers/{id}` | Müşteri siler                 |

---

## 📌 Örnek JSON

**POST /api/customers**

```json
{
  "firstName": "Enes",
  "lastName": "Kumaş",
  "email": "enes@example.com",
  "location": "Ankara"
}
```

**Response**

```json
{
  "id": 1,
  "firstName": "Enes",
  "lastName": "Kumaş",
  "email": "enes@example.com",
  "location": "Ankara"
}
```

---

## 🔧 Geliştirme Notları

* MapStruct ile entity ↔ DTO dönüşümleri yapılmaktadır.
* GlobalExceptionHandler ile tüm özel ve genel exception’lar yönetiliyor.
* Config Server ile ortam bazlı konfigürasyon sağlanıyor.
* Spring Boot Actuator `/actuator/refresh` endpoint’i ile konfigürasyon canlı yenilenebilir.
* Lombok kullanılarak boilerplate kod azaltıldı.

---

## 👨‍💻 Geliştirici

* [Enes Kumaş](https://github.com/eneskums)
