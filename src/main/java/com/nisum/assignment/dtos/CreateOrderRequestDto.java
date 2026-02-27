package com.nisum.assignment.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequestDto {
    @NotBlank
    public UUID customerId;
    public List<OrderItemDto> orderItems;

}
