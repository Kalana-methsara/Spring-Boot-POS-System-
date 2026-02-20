package org.example.posbackend.service;

import org.example.posbackend.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService{
    void saveCustomer(CustomerDto customerDto);
    void updateCustomer(CustomerDto customerDto);
    void deleteCustomer(String id);
    List<CustomerDto> getAllCustomers();

}
