package br.com.compass.msbffshop.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.compass.msbffshop.client.CustomerClient;
import br.com.compass.msbffshop.client.entity.Customer;

@Service
public class CustomerDetailServiceImp implements UserDetailsService{

	@Autowired
	private CustomerClient customerClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerClient.findCustomerByEmail(username);
		if (customer == null) {
			throw new UsernameNotFoundException("User login " + username + " not found.");
		}
		return new CustomerDetailsData(customer);
	}
}
