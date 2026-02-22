package com.nisum.assignment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEntity customer;

    @Column(name="created_at", nullable=false, updatable=false)
    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private OrderStatus status = OrderStatus.INPROGRESS;

    @Column(name="completed_at")
    private Instant completedAt;
}
