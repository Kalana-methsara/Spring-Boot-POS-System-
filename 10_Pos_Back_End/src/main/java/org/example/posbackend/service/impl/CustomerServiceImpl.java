package org.example.posbackend.service.impl;

import org.example.posbackend.dto.CustomerDto;
import org.example.posbackend.entity.Customer;
import org.example.posbackend.exceptions.CustomException;
import org.example.posbackend.repository.CustomerRepository;
import org.example.posbackend.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("CustomerDto is null");
        }
        if(customerRepository.existsById(customerDto.getId())) {
            throw new CustomException("Customer with id " + customerDto.getId() + " already exists");
        }
        customerRepository.save(modelMapper.map(customerDto, Customer.class));
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new NullPointerException("CustomerDto is null");
        }

        if (!customerRepository.existsById(customerDto.getId())) {
            throw new CustomException("Customer with id " + customerDto.getId() + " does not exist");
        }

        customerRepository.save(modelMapper.map(customerDto, Customer.class));
    }

    @Override
    public void deleteCustomer(String id) {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }else {
            throw new CustomException("Customer with id " + id + " does not exist");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .toList();
    }

}
