package com.nisum.assignment.repositories;

import com.nisum.assignment.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Query("""
        SELECT o FROM OrderEntity o
        LEFT JOIN FETCH o.orderItems oi
        LEFT JOIN FETCH oi.item
        WHERE o.id = :id
    """)
    Optional<OrderEntity> findByIdWithItems(UUID id);
}
