package com.example.gymtracker.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class TimeUtils {

    public static Date convertLocalDateToDate() {

        LocalDate localDate = LocalDate.now();
        if (localDate == null) {
            throw new IllegalArgumentException("LocalDate cannot be null");
        }

        // Convert LocalDate to LocalDateTime (default time set to start of day)
        LocalDateTime localDateTime = localDate.atStartOfDay();

        // Convert LocalDateTime to Instant (UTC time zone)
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

        // Convert Instant to Date
        return Date.from(instant);
    }

    public static void main(String[] args) {
        // Example usage
        LocalDate localDate = LocalDate.now(); // Current date
        Date date = convertLocalDateToDate();

        // Print the results
        System.out.println("LocalDate: " + localDate);
        System.out.println("Converted Date: " + date);
    }
}
