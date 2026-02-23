package com.nisum.assignment.dtos;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateItemResponseDto {
    private UUID id;
    private String name;
    private double price;
}
