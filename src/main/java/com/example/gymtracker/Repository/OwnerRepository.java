package com.example.gymtracker.Repository;

import com.example.gymtracker.Domain.entities.Owners;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends CrudRepository<Owners,Long> {

    Optional<Owners> findByUserName(String userName);
}
