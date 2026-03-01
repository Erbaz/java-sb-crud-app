package com.nisum.assignment.repositories;

import com.nisum.assignment.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    List<CustomerEntity> findByOrders_Id(UUID id);

    Optional<CustomerEntity> findByEmail(String email);
}
