package nl.vrecon.rest.customerservice;

import nl.vrecon.rest.customerservice.domain.Customer;
import nl.vrecon.rest.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

public class DBInit implements CommandLineRunner {

	@Autowired
    CustomerRepository customerRepository;

	
	@Override
	public void run(String... args) throws Exception {

		   Customer  customer = new Customer();
		   customer.setCustomerName("Mark de Haan");
		   
		   //Date dob = Date.from(LocalDate.of(1980, 03, 16).atStartOfDay(ZoneId.systemDefault()).toInstant());
		   
		   customer.setDateofBirth(LocalDate.of(1980, 03, 16));
		   customer.setPhoneNumber("+610452623758");
		   customer = customerRepository.save(customer);
		   


		   Customer  customer1 = new Customer();
		   customer1.setCustomerName("Andre Davids");
		   
		   customer1.setDateofBirth(LocalDate.of(1985, 03, 16));
		   customer1.setPhoneNumber("+610452623798");
		   customer1 = customerRepository.save(customer1);

           

	}

}
