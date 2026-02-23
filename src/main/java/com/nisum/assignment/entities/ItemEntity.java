package com.nisum.assignment.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String name;

    private double price;
}
