package hack.dev.jigsaw.support_desk.controller;

import hack.dev.jigsaw.support_desk.service.CustomerService;
import hack.dev.jigsaw.support_desk.service.SmsSenderService;
import hack.dev.jigsaw.support_desk.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceDeskController {

	@Autowired
	private SmsSenderService smsSender;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("customer/{customerId}/sms/send/{toNumber}/{message}")
	public boolean raiseTicketThroughSms(@PathVariable(value="customerId") Integer customerId
			, @PathVariable(value="toNumber") String toNumber
			, @PathVariable(value="message") String message) {
		this.customerService.loadCustomers();
		boolean status = false;
		String responseMessage = "Your previous sent request is faulty.\r\nPlease try to register again!";
		message = this.ticketService.createTicket(customerId, message);
		if(message != null) {
			responseMessage = ("You've successfully registered your request.\r\nYour ticket id is '" + customerId + "-" + message + "'");
		}
		status = this.smsSender.sendMessage(null, toNumber, responseMessage);
		return status;
	}
}
