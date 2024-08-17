package com.example.gymtracker.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String frequency;
    private String type;
}
