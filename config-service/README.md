# Config Service

Bu servis, **Spring Cloud Config Server** kullanarak merkezi konfigürasyon yönetimi sağlar.

Tüm mikroservislerin konfigürasyon dosyaları (`application.yml`) ayrı bir GitHub repository’sinde (**config-repo**) tutulur ve buradan otomatik olarak çekilir.

Config Service sayesinde:

* Tüm servisler tek bir merkezden konfigürasyon alır.
* Konfigürasyonlar Git üzerinden versiyonlanır.
* Servisler arasında tutarlılık sağlanır.

---

## 🚀 Kullanılan Teknolojiler

* **Java 21**
* **Spring Boot 3**
* **Spring Cloud 2025.0.0**

    * Config Server
    * Eureka Client
* **Actuator** (health & metrics)
* **Maven**
* **Docker**

---

## 📂 Proje Yapısı

```
config-service/
 ├── src/
 │   └── main/
 │       ├── java/com/eneskumas/configservice/ConfigServiceApplication.java
 │       └── resources/application.yml
 ├── pom.xml
 ├── Dockerfile
 └── README.md
```

---

## ⚙️ Konfigürasyon

`application.yml` içeriği:

```yaml
spring:
  application:
    name: config-service
  cloud:
    config:
      server:
        git:
          uri: https://github.com/eneskums/microservices-monorepo-example
          search-paths: config-files
          clone-on-start: true
          default-label: main

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: ${DISCOVERY_SERVICE_URL:http://localhost:8761/eureka/}

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
config-files repoda hangi path'de yer alıyorsa **search-paths**'e o path değeri girilmelidir. Eğer dosyalar path altında değil doğrudan repoda ise **search-paths** ayarını kaldırınız. Ayrıca reponuz **private** olacaksa `username` ve `password` bilgileri eklenmelidir.
Public repo kullanıyorsanız sadece `uri` alanı yeterlidir.

---

## ▶️ Çalıştırma

### 1. Maven ile

```bash
mvn spring-boot:run
```

### 2. Jar ile

```bash
mvn clean package -DskipTests
java -jar target/config-service-0.0.1-SNAPSHOT.jar
```

### 3. Docker ile

```bash
mvn clean package -DskipTests
docker build -t config-service .
docker run -p 8888:8888 config-service
```

---

## 🌐 Servis Adresleri

* **Config Server** → [http://localhost:8888](http://localhost:8888)
* **Actuator** → [http://localhost:8888/actuator](http://localhost:8888/actuator)

Örnek kullanım:

```http
GET http://localhost:8888/customer-service/default
```

Bu istek `config-repo/customer-service.yml` dosyasını döndürür.

---

## 📌 Geliştirme Notları

* `spring-boot-starter-web` bağımlılığı debug için bırakıldı, istenirse kaldırılabilir.
* Actuator endpoint’leri sayesinde sağlık durumu kolayca izlenebilir.
* Daha ileri seviye için `spring-cloud-bus` eklenerek config değişikliklerinin canlı servislerde otomatik yenilenmesi sağlanabilir.

---

## 👨‍💻 Geliştirici

* [Enes Kumaş](https://github.com/eneskums)
