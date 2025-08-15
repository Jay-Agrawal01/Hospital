package com.hospital.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleUtil {
    private static final Scanner scanner = new Scanner(System.in);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void displayMenu() {
        System.out.println("\n=== Hospital Management System ===");
        System.out.println("1. Add New Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. Schedule Appointment");
        System.out.println("4. View Appointments by Patient");
        System.out.println("5. View Appointments by Doctor");
        System.out.println("6. View Appointments by Date");
        System.out.println("7. Cancel Appointment");
        System.out.println("8. Exit");
        System.out.print("\nEnter your choice: ");
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " (YYYY-MM-DD): ");
                String input = scanner.nextLine().trim();
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            }
        }
    }

    public static LocalTime getTimeInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " (HH:MM): ");
                String input = scanner.nextLine().trim();
                return LocalTime.parse(input, TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:MM format (24-hour).");
            }
        }
    }

    public static LocalDateTime getDateTimeInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " (YYYY-MM-DD HH:MM): ");
                String input = scanner.nextLine().trim();
                return LocalDateTime.parse(input, DATE_TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date/time format. Please use YYYY-MM-DD HH:MM format (24-hour).");
            }
        }
    }

    public static void displayMessage(String message) {
        System.out.println("\n" + message);
    }

    public static void displayError(String error) {
        System.err.println("\nError: " + error);
    }

    public static void displayHeader(String header) {
        System.out.println("\n=== " + header + " ===");
    }
}
