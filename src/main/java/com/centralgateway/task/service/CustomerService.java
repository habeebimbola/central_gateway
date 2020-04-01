package com.centralgateway.task.service;

import com.centralgateway.task.Customer;
import com.centralgateway.task.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerService  {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(Integer id )
    {
        return customerRepository.findById(id).get();
    }

    public void deleteCustomer(Integer id ){
        this.customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Integer id, Customer customer){
        this.customerRepository.findById(id).ifPresent((cust -> {
            cust.setAddress(customer.getAddress());
            cust.setEmail(customer.getEmail());
            cust.setFullname(customer.getFullname());
            cust.setMobile(customer.getMobile());

            this.customerRepository.save(cust);
        }));
        return customer;
    }

    public Customer createCustomer(Customer customer){
        return this.customerRepository.save(customer);
    }
}
