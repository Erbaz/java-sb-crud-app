package com.nisum.assignment.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateItemRequestDto {

    @NotBlank(message = "Name is required")
    public String name;

    @NotBlank(message = "Price is required")
    public double price;

}
