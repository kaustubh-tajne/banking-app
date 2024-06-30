package com.hcl.bankingservice;

import com.hcl.bankingservice.repository.AccountRepository;
import com.hcl.bankingservice.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "Authorization", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class BankingServiceApplication implements CommandLineRunner {

//	@Autowired
//	private final CustomerRepository customerRepository;
//	private final AccountRepository accountRepository;
//
//    public BankingServiceApplication(CustomerRepository customerRepository, AccountRepository accountRepository) {
//        this.customerRepository = customerRepository;
//        this.accountRepository = accountRepository;
//    }

    public static void main(String[] args) {
		SpringApplication.run(BankingServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Customer customer = new Customer();
//		customer.setFirstName("FName");
//		customer.setLastName("LName");
//		customer.setEmail("mail@gmail.com");
//		customer.setDateOfBirth(LocalDate.parse("2002-04-19"));
//		customer.setPhoneNumber("88867868687");
//
////		Customer savedCustomer = customerRepository.save(customer);
////		System.out.println(savedCustomer);
//
//		Address address = new Address();
//		address.setPlotNumber("56");
//		address.setArea("Sonbaji Nagar");
//		address.setCity("Nagpur");
//		address.setState("MH");
//		address.setZipCode("440035");
//		address.setCountry("India");
//		address.setCustomer(customer);
//
//		customer.getAddresses().add(address);
//		Customer savedCustomer = customerRepository.save(customer);
//		System.out.println(savedCustomer);
//
////		Address savedAddress = addressRepository.save(address);
////		System.out.println(savedAddress);
//
////		customerRepository.delete(customer);
//
//		Optional<Customer> optionalCustomer = customerRepository.findById((long) 1);
//		optionalCustomer.get().getAddresses().forEach(address1 -> System.out.println(address1));
//
//
//		Account account = new Account();
//		account.setAccountNumber("123231312");
//		account.setBalance(5000);
//		account.setDateOfOpening(LocalDate.parse("2024-01-01"));
//		account.setAccountType(AccountType.SAVING);
//		account.setCustomer(customer);
//
////		Account savedAccount = accountRepository.save(account);
////		System.out.println(savedAccount);
//
//		Account account1 = new Account();
//		account1.setAccountNumber("123231312");
//		account1.setBalance(5000);
//		account1.setDateOfOpening(LocalDate.parse("2024-01-01"));
//		account1.setAccountType(AccountType.SAVING);
//		account1.setCustomer(customer);
//
////		Account savedAccount1 = accountRepository.save(account1);
////		System.out.println(savedAccount1);
//
//		customer.getAccounts().add(account);
//		customer.getAccounts().add(account1);
//
//		final Customer customer1 = customerRepository.save(customer);
//		System.out.println(customer1);
//
//		Optional<Customer> optionalCustomer1 = customerRepository.findById((long) 1);
//		System.out.println(optionalCustomer1.get());
//		optionalCustomer1.get().getAccounts().forEach(account2 -> System.out.println(account2));
	}
}
