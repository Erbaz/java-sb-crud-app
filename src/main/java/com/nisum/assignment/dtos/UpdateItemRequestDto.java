package com.nisum.assignment.dtos;
import jakarta.annotation.Nullable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateItemRequestDto {
    public String name;
    public Double price;
}
