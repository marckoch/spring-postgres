package de.marckoch.springpostgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class SpringPostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPostgresApplication.class, args);
	}

	@Autowired
	private CustomerRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		List<Customer> allCustomers = this.repository.findAll();
		System.out.println("Number of customers: " + allCustomers.size());

		Customer newCustomer = new Customer();
		newCustomer.setFirstName("John");
		newCustomer.setLastName("Doe");
		System.out.println("Saving new customer...");
		this.repository.save(newCustomer);

		allCustomers = this.repository.findAll();
		System.out.println("Number of customers: " + allCustomers.size());
	}
}
