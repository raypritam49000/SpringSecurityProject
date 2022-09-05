package net.corejava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.corejava.entity.Customer;
import net.corejava.repository.CustomerRepository;


public class CustomCustomerDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer customer = this.customerRepository.findByEmail(username);

		if (customer == null) {
			throw new UsernameNotFoundException("No User found for given email");
		}

		return new CustomCustomerDetails(customer);
	}

}

