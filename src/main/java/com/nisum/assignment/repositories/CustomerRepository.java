package com.nisum.assignment.repositories;

import com.nisum.assignment.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByOrders_Id(UUID id);
}
