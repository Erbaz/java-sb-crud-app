package com.nisum.assignment.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateItemRequestDto {

    @NotBlank(message = "Name is required")
    public String name;

    @NotNull(message = "Price is required")
    @Positive
    public double price;

}
