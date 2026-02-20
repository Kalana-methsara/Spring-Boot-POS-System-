package org.example.posbackend.controller;

import jakarta.validation.Valid;
import org.example.posbackend.dto.CustomerDto;
import org.example.posbackend.service.CustomerService;
import org.example.posbackend.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> saveCustomer(@RequestBody @Valid CustomerDto customerDto) {

        customerService.saveCustomer(customerDto);

        return new ResponseEntity<>(
                new APIResponse<>(
                        201,
                        "Customer Created Successfully",
                        null
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updateCustomer(@RequestBody @Valid CustomerDto customerDto) {

        customerService.updateCustomer(customerDto);

        return ResponseEntity.ok(
                new APIResponse<>(
                        200,
                        "Customer Updated Successfully",
                        null
                )
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteCustomer(@PathVariable @Valid String id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.ok(
                new APIResponse<>(
                        200,
                        "Customer Deleted Successfully",
                        null
                )
        );
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<CustomerDto>>> getAllCustomers() {

        List<CustomerDto> customerList = customerService.getAllCustomers();

        return ResponseEntity.ok(
                new APIResponse<>(
                        200,
                        "Customer List Fetched Successfully",
                        customerList
                )
        );
    }
}
