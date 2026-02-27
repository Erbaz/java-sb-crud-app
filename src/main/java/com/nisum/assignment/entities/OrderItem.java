package com.nisum.assignment.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ItemEntity item;

    @Column(nullable = false)
    private Integer quantity;
}
