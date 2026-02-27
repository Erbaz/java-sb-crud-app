package com.nisum.assignment.dtos;

import com.nisum.assignment.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    public UUID id;
    public UUID customerId;
    public List<ItemDto> items;
    public OrderStatus status;
}
