package com.hospital;

import com.hospital.dao.AppointmentDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.model.Appointment;
import com.hospital.model.Patient;
import com.hospital.util.ConsoleUtil;
import com.hospital.util.DatabaseConnection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    private static final PatientDAO patientDAO = new PatientDAO();
    private static final AppointmentDAO appointmentDAO = new AppointmentDAO();
    private static final String[] DOCTORS = {"Dr. Smith", "Dr. Johnson", "Dr. Williams", "Dr. Brown"};

    public static void main(String[] args) {
        initializeDatabase();
        showMainMenu();
        DatabaseConnection.closeConnection();
    }

    private static void initializeDatabase() {
        // This would typically be in a SQL script, but we'll create tables programmatically for simplicity
        try (var conn = DatabaseConnection.getConnection();
             var stmt = conn.createStatement()) {
            
            // Create patients table
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS patients (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "phone VARCHAR(20) NOT NULL, " +
                "email VARCHAR(100), " +
                "date_of_birth DATE NOT NULL, " +
                "address TEXT, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")"
            );

            // Create appointments table
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS appointments (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "patient_id INT NOT NULL, " +
                "doctor_name VARCHAR(100) NOT NULL, " +
                "appointment_time DATETIME NOT NULL, " +
                "purpose TEXT, " +
                "status VARCHAR(20) DEFAULT 'Scheduled', " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE" +
                ")"
            );
            
        } catch (Exception e) {
            ConsoleUtil.displayError("Error initializing database: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void showMainMenu() {
        while (true) {
            ConsoleUtil.displayMenu();
            int choice = ConsoleUtil.getIntInput("");

            switch (choice) {
                case 1:
                    addNewPatient();
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    scheduleAppointment();
                    break;
                case 4:
                    viewAppointmentsByPatient();
                    break;
                case 5:
                    viewAppointmentsByDoctor();
                    break;
                case 6:
                    viewAppointmentsByDate();
                    break;
                case 7:
                    cancelAppointment();
                    break;
                case 8:
                    ConsoleUtil.displayMessage("Thank you for using Hospital Management System!");
                    return;
                default:
                    ConsoleUtil.displayError("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewPatient() {
        ConsoleUtil.displayHeader("Add New Patient");
        
        String name = ConsoleUtil.getStringInput("Enter patient's full name");
        String phone = ConsoleUtil.getStringInput("Enter phone number");
        String email = ConsoleUtil.getStringInput("Enter email address (optional)");
        LocalDate dob = ConsoleUtil.getDateInput("Enter date of birth");
        String address = ConsoleUtil.getStringInput("Enter address");
        
        Patient patient = new Patient(name, phone, email, dob, address);
        patientDAO.createPatient(patient);
        
        ConsoleUtil.displayMessage("Patient added successfully with ID: " + patient.getId());
    }

    private static void viewAllPatients() {
        ConsoleUtil.displayHeader("All Patients");
        var patients = patientDAO.getAllPatients();
        
        if (patients.isEmpty()) {
            ConsoleUtil.displayMessage("No patients found.");
            return;
        }
        
        for (Patient patient : patients) {
            System.out.printf("ID: %d, Name: %s, Phone: %s, Email: %s%n",
                    patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail());
        }
    }

    private static void scheduleAppointment() {
        ConsoleUtil.displayHeader("Schedule New Appointment");
        
        // Show list of patients
        viewAllPatients();
        int patientId = ConsoleUtil.getIntInput("\nEnter patient ID");
        
        // Show available doctors
        System.out.println("\nAvailable Doctors:");
        for (int i = 0; i < DOCTORS.length; i++) {
            System.out.printf("%d. %s%n", i + 1, DOCTORS[i]);
        }
        
        int doctorChoice = ConsoleUtil.getIntInput("Select doctor (1-" + DOCTORS.length + ")");
        if (doctorChoice < 1 || doctorChoice > DOCTORS.length) {
            ConsoleUtil.displayError("Invalid doctor selection.");
            return;
        }
        
        String doctorName = DOCTORS[doctorChoice - 1];
        LocalDateTime appointmentTime = ConsoleUtil.getDateTimeInput("Enter appointment date and time (YYYY-MM-DD HH:MM)");
        
        // Check if the time slot is available
        if (!isTimeSlotAvailable(doctorName, appointmentTime)) {
            ConsoleUtil.displayError("The selected time slot is not available. Please choose another time.");
            return;
        }
        
        String purpose = ConsoleUtil.getStringInput("Enter purpose of visit");
        
        Appointment appointment = new Appointment(patientId, doctorName, appointmentTime, purpose);
        appointmentDAO.createAppointment(appointment);
        
        ConsoleUtil.displayMessage(String.format("Appointment scheduled successfully!%n" +
                "Appointment ID: %d%n" +
                "Doctor: %s%n" +
                "Date/Time: %s",
                appointment.getId(),
                doctorName,
                appointmentTime.format(ConsoleUtil.DATE_TIME_FORMAT)));
    }

    private static boolean isTimeSlotAvailable(String doctorName, LocalDateTime dateTime) {
        // In a real application, you would check the database for existing appointments
        // For simplicity, we'll just check if the time is during working hours (9 AM - 5 PM)
        LocalTime time = dateTime.toLocalTime();
        return !time.isBefore(LocalTime.of(9, 0)) && !time.isAfter(LocalTime.of(17, 0));
    }

    private static void viewAppointmentsByPatient() {
        ConsoleUtil.displayHeader("View Appointments by Patient");
        int patientId = ConsoleUtil.getIntInput("Enter patient ID");
        
        var appointments = appointmentDAO.getAppointmentsByPatient(patientId);
        displayAppointments(appointments);
    }

    private static void viewAppointmentsByDoctor() {
        ConsoleUtil.displayHeader("View Appointments by Doctor");
        
        System.out.println("Available Doctors:");
        for (int i = 0; i < DOCTORS.length; i++) {
            System.out.printf("%d. %s%n", i + 1, DOCTORS[i]);
        }
        
        int choice = ConsoleUtil.getIntInput("Select doctor (1-" + DOCTORS.length + ")");
        if (choice < 1 || choice > DOCTORS.length) {
            ConsoleUtil.displayError("Invalid doctor selection.");
            return;
        }
        
        var appointments = appointmentDAO.getAppointmentsByDoctor(DOCTORS[choice - 1]);
        displayAppointments(appointments);
    }

    private static void viewAppointmentsByDate() {
        ConsoleUtil.displayHeader("View Appointments by Date");
        LocalDate date = ConsoleUtil.getDateInput("Enter date (YYYY-MM-DD)");
        
        var appointments = appointmentDAO.getAppointmentsByDate(date);
        displayAppointments(appointments);
    }

    private static void cancelAppointment() {
        ConsoleUtil.displayHeader("Cancel Appointment");
        int appointmentId = ConsoleUtil.getIntInput("Enter appointment ID to cancel");
        
        if (appointmentDAO.cancelAppointment(appointmentId)) {
            ConsoleUtil.displayMessage("Appointment cancelled successfully!");
        } else {
            ConsoleUtil.displayError("Failed to cancel appointment. Please check the appointment ID and try again.");
        }
    }

    private static void displayAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            ConsoleUtil.displayMessage("No appointments found.");
            return;
        }
        
        for (Appointment appt : appointments) {
            System.out.printf("ID: %d, Patient ID: %d, Doctor: %s, Time: %s, Purpose: %s, Status: %s%n",
                    appt.getId(),
                    appt.getPatientId(),
                    appt.getDoctorName(),
                    appt.getAppointmentTime().format(ConsoleUtil.DATE_TIME_FORMAT),
                    appt.getPurpose(),
                    appt.getStatus());
        }
    }
}
