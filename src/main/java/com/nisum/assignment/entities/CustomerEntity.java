package com.nisum.assignment.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, orphanRemoval = true)
    private java.util.List<OrderEntity> orders = new ArrayList<>();

    @Column(nullable=false)
    private String name;

    @Column
    private String address;
}
