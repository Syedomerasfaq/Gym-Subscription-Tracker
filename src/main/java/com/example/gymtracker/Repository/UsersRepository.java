package com.example.gymtracker.Repository;

import com.example.gymtracker.Domain.entities.Owners;
import com.example.gymtracker.Domain.entities.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<List<Users>> findByEndDate(LocalDate endDate);
    @Query("SELECT u FROM Users u WHERE u.name LIKE %:name%")
    Optional<List<Users>> findByNameContaining(@Param("name") String name, Sort sort);

    @Query("SELECT u FROM Users u WHERE u.name LIKE %:name%")
    Optional<List<Users>> findByNameContaining(@Param("name") String name);
}
