# Microservices Monorepo Example

Bu proje, **Spring Boot + Spring Cloud** kullanılarak geliştirilmiş mikroservis tabanlı bir örnek projedir.  

Tüm servisler tek bir monorepo altında yönetilir.

---

## 🚀 Kullanılan Teknolojiler

* **Java 21**
* **Spring Boot 3**
* **Spring Cloud 2025.0.0**
    * Config Server
    * Eureka Discovery Server
    * Spring Cloud Gateway
* **Spring Data JPA (MySQL)**
* **MapStruct**
* **Lombok**
* **OpenFeign / Feign Client**
* **Resilience4j**
* **Actuator**
* **Maven**
* **Docker & Docker Compose**

---

## 📂 Monorepo Yapısı

```
microservices-monorepo-example/
 ├── config-service/ → Merkezi konfigürasyon yönetimi
 ├── customer-service/ → Müşteri yönetimi
 ├── discovery-service/ → Eureka Service Discovery
 ├── gateway-service/ → API Gateway
 ├── order-service/ → Sipariş yönetimi
 ├── config-files/ → application.yml dosyaları
 ├── docker-compose.yml
 └── README.md
```

---

## ⚙️ Servisler

### 1. Config Service
- Merkezi konfigürasyon yönetimi sağlar.
- `config-files/` altındaki dosyaları okur.

### 2. Discovery Service
- Eureka sunucusu.
- Servislerin birbirini keşfetmesini sağlar.

### 3. Gateway Service
- Tüm isteklerin giriş noktasıdır.
- Servisler arası routing yapar.

### 4. Customer Service
- Basit müşteri yönetimi mikroservisi.

### 5. Order Service
- Basit sipariş yönetimi mikroservisi.

---

## ▶️ Çalıştırma

### 1. Maven ile

Her servis kendi klasöründe:

```bash
mvn spring-boot:run
```

### 2. Jar ile

Her servis kendi klasöründe:

```bash
mvn clean package -DskipTests
java -jar target/<service-name>-0.0.1-SNAPSHOT.jar
```

### 3. Docker ile

Her servis kendi klasöründe:

```bash
mvn clean package -DskipTests
docker build -t <service-name> .
docker run -p <service-port>:<service-port> <service-name>
```

### 4. DockerCompose ile

Her servisin paketlenmiş olması gerekir. Yani her servis için kendi klasöründe **mvn clean package -DskipTests** komutu çalıştırılmış olmalıdır.

```bash
docker-compose up --build -d
```

---

## 🌐 Servis Adresleri

* **Config Service** → [http://localhost:8888](http://localhost:8888)
* **Discovery Service** → [http://localhost:8761](http://localhost:8761)
* **Gateway Service** → [http://localhost:8089](http://localhost:8089)
* **Customer Service (gateway üzerinden)** → [http://localhost:8089/api/customers](http://localhost:8089/api/customers)
* **Discovery Service (gateway üzerinden)** → [http://localhost:8089/api/orders](http://localhost:8089/api/orders)

---

## 👨‍💻 Geliştirici

* [Enes Kumaş](https://github.com/eneskums)
