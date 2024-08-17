package com.example.gymtracker.Repository;

import com.example.gymtracker.Domain.entities.Owners;
import com.example.gymtracker.Domain.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {
    Optional<List<Users>> findByEndDate(LocalDate endDate);
}
