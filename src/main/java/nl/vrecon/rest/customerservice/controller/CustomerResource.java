package nl.vrecon.rest.customerservice.controller;

import nl.vrecon.rest.customerservice.domain.Customer;
import nl.vrecon.rest.customerservice.exception.ResourceNotFoundException;
import nl.vrecon.rest.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CustomerResource {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping(value = "/customers")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Customer save(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	 }
	
	@GetMapping(value = "/customers") 
	   public List<Customer> all (){
              return customerRepository.findAll();
	         
	   } 

	@GetMapping(value = "/customers/{customerId}") 
	   public Customer findByCustomerId (@PathVariable Integer customerId){ 
	         return customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));
	   }
	
	@DeleteMapping(value = "/customers/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId){

		return customerRepository.findById(customerId).map(customer -> {
		customerRepository.delete(customer);
		return ResponseEntity.ok().build();
		}
        ).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

	}
	
	@PutMapping(value = "/customers/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId,@RequestBody Customer newCustomer){
		
		return customerRepository.findById(customerId).map(customer -> {
			customer.setCustomerName(newCustomer.getCustomerName());
			customer.setDateofBirth(newCustomer.getDateofBirth());
			customer.setPhoneNumber(newCustomer.getPhoneNumber());
			customerRepository.save(customer);
			return ResponseEntity.ok(customer);
		}).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));
		
	}

}
