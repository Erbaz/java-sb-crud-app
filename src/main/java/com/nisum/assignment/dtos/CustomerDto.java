package com.nisum.assignment.dtos;

import com.nisum.assignment.entities.OrderEntity;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    public String id;
    public String name;
    public String address;
    public List<OrderEntity> orders;
}
