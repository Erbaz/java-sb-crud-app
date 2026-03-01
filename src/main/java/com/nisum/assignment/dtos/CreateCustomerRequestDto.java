package com.nisum.assignment.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRequestDto {

    @NotBlank(message = "Name is required")
    public String name;

    @NotBlank(message = "Address is required")
    public String address;

    @Email
    @NotBlank(message = "Email is required")
    public String email;

    @NotBlank(message = "Password is required")
    public String password;
}
