package com.centralgateway.task;

import com.centralgateway.task.repository.CustomerRepository;
import com.centralgateway.task.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path ="/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){

        if(customer == null )
        {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        this.customerService.createCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @PutMapping(path = "/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Integer id){

        if(( customer == null) || (id == null) )
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.customerService.updateCustomer(id,customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping( "/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable Integer id){
        this.customerService.deleteCustomer(id);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id){

        Customer customer = this.customerService.getCustomer(id);
        if(customer == null ){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK );
    }
}
