package com.nisum.assignment.dtos;

import com.nisum.assignment.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderStatusRequestDto {
    public OrderStatus status;
}
