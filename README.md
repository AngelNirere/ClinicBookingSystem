# 🏥 Smart Clinic Booking Management System

## 📌 Project Overview

The **Smart Clinic Booking Management System** is a Spring Boot–based application developed to automate and streamline appointment booking in a healthcare clinic. The system reduces manual scheduling errors, prevents double booking, and automates consultation fee calculation using clean architecture and design patterns.

This project was developed as a **Final Exam Project for Best Programming Practice and Design Patterns**.

---

## 👨‍🎓 Student Information

* **Student Name:** Nirere Angelique
* **Student ID:** 26564
* **Course:** Best Programming Practice & Design Patterns
* **Date:** December 2025

---

## 🚀 Features

* 📅 Appointment booking and management
* 👨‍⚕️ Doctor availability validation
* 💰 Automated consultation fee calculation
* 🏷️ Multiple consultation service types
* 🗄️ Persistent data storage using H2 database

---

## 🛠️ Technology Stack

* **Java:** 17
* **Framework:** Spring Boot 3.0
* **Database:** H2 In-Memory Database
* **ORM:** Spring Data JPA
* **Build Tool:** Maven
* **Utilities:** Lombok
* **Testing:** JUnit 5
* **Version Control:** Git
* **Deployment:** Docker

---

## ⚙️ Design Patterns Used

### 🏭 Factory Design Pattern

The system uses the **Factory Design Pattern** to manage consultation fee calculation logic.

#### 🔹 How It Works

* `ConsultationFeeFactory` selects the appropriate fee strategy based on consultation type.
* Each service type has its own pricing implementation.

#### 🔹 Implementations

* `GeneralConsultationFee` – standard consultation pricing
* `SpecialistConsultationFee` – higher specialist consultation pricing

#### 🔹 Benefits

* Follows the **Open/Closed Principle**
* Easy to extend with new consultation types
* Separates business logic from controllers

---

## 🧱 Project Structure

```bash
src/main/java
├── controller
├── service
├── factory
├── model
├── repository
└── ClinicBookingSystemApplication.java
```

---

## 🗄️ Database Configuration

* **Database:** H2 (In-Memory)
* **Console URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* **JDBC URL:** jdbc:h2:mem:testdb
* **Username:** sa
* **Password:** (empty)

---

## ▶️ How to Run the Project

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-AngelNirere/clinic-booking-system.git
```

### 2️⃣ Navigate to Project Directory

```bash
cd clinic-booking-system
```

### 3️⃣ Run the Application

```bash
mvn spring-boot:run
```

### 4️⃣ Access the Application

* Application: [http://localhost:8080](http://localhost:8080)
* H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 🧪 Testing

Unit tests are written using **JUnit 5** to validate:

* Appointment booking logic
* Fee calculation strategies
* Factory pattern behavior

Run tests using:

```bash
mvn test
```

---

## 📦 Docker Support

The project supports containerization using Docker.

```bash
docker build -t clinic-booking-system .
docker run -p 8080:8080 clinic-booking-system
```

---

## ✅ Conclusion

The **Smart Clinic Booking Management System** demonstrates clean code practices, layered architecture, and effective use of the Factory Design Pattern. It is scalable, maintainable, and suitable for real-world clinic appointment management.

---

## 📄 License

This project is developed for academic purposes only.
