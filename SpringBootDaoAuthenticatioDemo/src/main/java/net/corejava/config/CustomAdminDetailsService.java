package net.corejava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.corejava.entity.Admin;
import net.corejava.repository.AdminRepository;


public class CustomAdminDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Admin admin = this.adminRepository.findByEmail(username);

		if (admin == null) {
			throw new UsernameNotFoundException("No User found for given email");
		}

		return new CustomAdminDetails(admin);
	}

}
