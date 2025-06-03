# Parallel Price Scraper

**Parallel Price Scraper** is an educational project built with Java 21, focused on studying and practically applying Virtual Threads (Java Project Loom) in I/O-intensive scenarios such as web scraping. The main goal is to explore the benefits of Virtual Threads in concurrent tasks involving high-latency HTTP calls, simulating a real-world data collection and persistence environment.

---

## Objectives

- Study and apply Virtual Threads in a hands-on way.
- Simulate a real-world parallel price scraping scenario.
- Persist the collected data in a local database (H2).
- Build a foundation for possible extensions such as scheduling, web interface, or REST API.

---

## Motivation

With the introduction of Virtual Threads in Java 21, it has become possible to handle thousands of simultaneous I/O-bound tasks without the traditional overhead of managing native threads. This project aims to demonstrate how this new approach improves scalability and simplifies writing concurrent code in practice.

---

## Technologies Used

- **Java 21** — LTS version with support for Virtual Threads.
- **HTTP Client (java.net.http)** — native client for asynchronous HTTP requests.
- **H2 Database** — lightweight embedded local database, ideal for testing and persistence.
- **JDBC** — direct database access.
- **Maven** — dependency and build management.

---

## Current Features

- Parallel execution of multiple HTTP requests using Virtual Threads.
- Latency simulation using test URLs (`httpbin.org/delay`).

---

## Project Structure

```
src/
├── main/
│ ├── java/
│ │ └── com/example/scraper/
│ │ ├── Main.java
│ │ ├── PriceScraper.java
│ │ ├── ProductPrice.java
│ │ ├── ProductPriceRepository.java
│ │ └── ScrapingService.java
│ └── resources/
│ └── application.properties
```

---

## How to Run

1. Clone the repository.
2. Compile the project using Maven:
```bash
  mvn clean compile exec:java -Dexec.mainClass=com.gutkoski.scraper.Main
```

---

## License
This project is open source and available under the MIT License.