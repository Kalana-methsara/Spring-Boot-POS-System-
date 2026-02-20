package org.example.posbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    @NotBlank(message = "Item Id is mandatory")
    private String id;
    @NotBlank(message = "Item name is mandatory")
    private String name;
    @Positive(message = "Price must be greater than 0")
    private double price;
    @Min(value = 0, message = "Quantity cannot be negative")
    private int qty;
}
