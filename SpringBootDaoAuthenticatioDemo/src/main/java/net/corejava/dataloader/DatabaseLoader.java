//package net.corejava.dataloader;
//
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import net.corejava.entity.Admin;
//import net.corejava.entity.Customer;
//import net.corejava.entity.Role;
//import net.corejava.repository.AdminRepository;
//import net.corejava.repository.CustomerRepository;
//
//@Configuration
//public class DatabaseLoader {
//
//	private final AdminRepository adminRepository;
//	private final CustomerRepository customerRepository;
//
//	public DatabaseLoader(AdminRepository adminRepository, CustomerRepository customerRepository) {
//		super();
//		this.adminRepository = adminRepository;
//		this.customerRepository = customerRepository;
//	}
//
//	@Bean
//	public CommandLineRunner intializeDatabase() {
//		return args -> {
//			Admin admin1 = new Admin("admin@corejava.net", "admin123", Role.ADMIN);
//			Admin admin2 = new Admin("david@gmail.com", "david123", Role.ADMIN);
//
//			Customer customer1 = new Customer("alex@gmail.com", "alex123", Role.CUSTOMER);
//			Customer customer2 = new Customer("jane@gmail.com", "jane123", Role.CUSTOMER);
//
//			adminRepository.saveAll(List.of(admin1, admin2));
//			customerRepository.saveAll(List.of(customer1, customer2));
//
//			System.out.println("Sample database initialized...");
//		};
//	}
//
//}
