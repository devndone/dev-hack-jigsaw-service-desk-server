package hack.dev.jigsaw.support_desk.service;

import hack.dev.jigsaw.support_desk.entity.Customer;
import hack.dev.jigsaw.support_desk.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	private static volatile boolean loaded;
	
	public void loadCustomers() {
		int i = 10;
		Customer c;
		if(!loaded) {
			while(i > 0) {
				c = new Customer();
				c.setName("Dev" + i);
				c.setEmailId("devndone@gmail.com");
				c.setMobileNumber("+917406002121");
				c.setTelephoneNumber("8040945171");
				c.setActiveStatus("Y");
				customerRepository.save(c);
				--i;
			}
			System.out.println(this.customerRepository.findAll());
			loaded = true;
		}
	}
}
