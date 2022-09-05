//package net.corejava.dataloader;
//
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import net.corejava.entity.Role;
//import net.corejava.entity.User;
//import net.corejava.repository.UserRepository;
//
//@Configuration
//public class DatabaseLoader {
//
//	private final UserRepository userRepository;
//
//	public DatabaseLoader(UserRepository userRepository) {
//		super();
//		this.userRepository = userRepository;
//	}
//
//	@Bean
//	public CommandLineRunner intializeDatabase() {
//		return args -> {
//			User user1 = new User("admin@corejava.net", "admin123", Role.ADMIN);
//			User user2 = new User("david@gmail.com", "david123", Role.ADMIN);
//			User user3 = new User("alex@gmail.com", "alex123", Role.USER);
//			User user4 = new User("jane@gmail.com", "jane123", Role.USER);
//
//			userRepository.saveAll(List.of(user1, user2, user3, user4));
//			
//			System.out.println("Sample database initialized...");
//		};
//	}
//
//}
