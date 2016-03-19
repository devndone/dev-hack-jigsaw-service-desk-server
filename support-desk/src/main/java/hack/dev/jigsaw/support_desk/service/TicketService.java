package hack.dev.jigsaw.support_desk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hack.dev.jigsaw.support_desk.entity.Customer;
import hack.dev.jigsaw.support_desk.entity.CustomerRequest;
import hack.dev.jigsaw.support_desk.repository.CustomerRepository;
import hack.dev.jigsaw.support_desk.repository.CustomerRequestRepository;

@Service
public class TicketService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerRequestRepository customerRequestRepository;
	
	public String createTicket(Integer customerId, String ticketDescription) {
		String res = null;
		Customer c = this.customerRepository.findOne(customerId);
		CustomerRequest cr = new CustomerRequest();
		cr.setCustomer(c);
		cr.setTicketDescription(ticketDescription);
		cr.setTicketStatus("Open");
		int ticketId = 1;
		if(c.getTickets() != null) {
			ticketId = (c.getTickets().size() + 1);
		}
		cr.setTicketId(String.valueOf(ticketId));
		try {
			this.customerRequestRepository.save(cr);
			res = String.valueOf(ticketId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
