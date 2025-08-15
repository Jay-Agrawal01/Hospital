package com.hospital.model;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int patientId;
    private String doctorName;
    private LocalDateTime appointmentTime;
    private String purpose;
    private String status; // Scheduled, Completed, Cancelled

    public Appointment() {}

    public Appointment(int patientId, String doctorName, LocalDateTime appointmentTime, String purpose) {
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.appointmentTime = appointmentTime;
        this.purpose = purpose;
        this.status = "Scheduled";
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorName='" + doctorName + '\'' +
                ", appointmentTime=" + appointmentTime +
                ", purpose='" + purpose + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
