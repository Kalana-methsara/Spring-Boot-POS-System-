package org.example.posbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

     @NotBlank(message = "Customer Id is mandatory")
     private String id;

     @NotBlank(message = "Customer name is mandatory")
     @Pattern(regexp = "[\\p{L} .'-]+$", message = "Customer name is incorrect")
     private String name;

     @NotBlank(message = "Email is mandatory")
     @Email(message = "Invalid email format")
     private String email;

     @NotBlank(message = "Phone number is mandatory")
     @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
     private String phone;
}