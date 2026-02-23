package com.nisum.assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    public String id;
    public String customerId;
    public String productName;
    public int quantity;
    public double price;
    public String status;
}
