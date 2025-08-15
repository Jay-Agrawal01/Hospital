# 🏥 Hospital Management System

A comprehensive Hospital Management System built with Java and MySQL, designed to streamline patient appointments and healthcare facility management. This project demonstrates strong object-oriented design principles, database integration, and clean code practices.

## 🚀 Features

### Patient Management
- **CRUD Operations**: Full lifecycle management of patient records
- **Detailed Profiles**: Store comprehensive patient information including contact details and medical history
- **Efficient Search**: Quickly locate patients by ID or name

### Appointment Scheduling
- **Intuitive Interface**: Easy-to-use console interface for scheduling appointments
- **Time Slot Management**: Prevents double-booking and ensures efficient doctor scheduling
- **Flexible Viewing**: View appointments by patient, doctor, or specific date
- **Status Tracking**: Track appointment status (Scheduled, Completed, Cancelled)

### Doctor Management
- **Doctor Schedules**: Manage multiple doctors with their respective schedules
- **Specialization Support**: Easy to extend for different medical specialties
- **Availability Tracking**: View doctor availability at a glance

## 🛠️ Technical Stack

- **Core**: Java 17
- **Database**: MySQL 8.0+
- **Build Tool**: Maven
- **JDBC**: For database connectivity
- **Design Patterns**:
  - Data Access Object (DAO) pattern for database operations
  - MVC architecture for clean separation of concerns
  - Factory pattern for database connection management

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- MySQL Server 8.0 or higher
- Maven 3.6 or higher

### Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Jay-Agrawal01/Hospital.git
   cd hospital-management-system
   ```

2. **Database Setup**
   ```sql
   CREATE DATABASE hospital_db;
   ```
   
   Update database credentials in `src/main/java/com/hospital/util/DatabaseConnection.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/hospital_db?useSSL=false&serverTimezone=UTC";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";
   ```

3. **Build and Run**
   ```bash
   # Build the project
   mvn clean package
   
   # Run the application
   mvn exec:java -Dexec.mainClass="com.hospital.Main"
   ```

## 🎯 Key Technical Decisions

1. **Database Design**
   - Normalized database schema for data integrity
   - Proper indexing for frequently queried fields
   - Foreign key constraints to maintain referential integrity

2. **Error Handling**
   - Comprehensive exception handling throughout the application
   - User-friendly error messages
   - Transaction management for data consistency

3. **Code Organization**
   - Clear separation of concerns (models, DAOs, utilities)
   - Meaningful class and method names
   - Proper documentation and comments

## 📊 Project Structure

```
src/main/java/com/hospital/
├── Main.java                  # Application entry point and main controller
├── model/                     # Data models (POJOs)
│   ├── Patient.java           # Patient entity with all relevant fields
│   └── Appointment.java       # Appointment entity with status tracking
├── dao/                       # Data Access Layer
│   ├── PatientDAO.java        # Database operations for patients
│   └── AppointmentDAO.java    # Database operations for appointments
└── util/                      # Utility classes
    ├── DatabaseConnection.java # Database connection management
    └── ConsoleUtil.java       # Console I/O utilities
```

## 🧪 Testing

To ensure reliability, the application includes:
- Input validation for all user inputs
- Boundary condition checks
- Database transaction handling
- Error recovery mechanisms

## 📝 Future Enhancements

- [ ] Implement user authentication and role-based access control
- [ ] Develop a web-based frontend using Spring Boot and React
- [ ] Add reporting module with analytics and statistics
- [ ] Integrate with electronic health record (EHR) systems
- [ ] Implement automated appointment reminders via email/SMS
- [ ] Add support for medical billing and insurance processing

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.


## 👨‍💻 Author

[Jay Agrawal]  
[jaycool9084@gmail.com]  

---

*Built with ❤️ and Java*
