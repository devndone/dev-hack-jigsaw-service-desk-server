package hack.dev.jigsaw.support_desk.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name="customer_request",
		uniqueConstraints=@UniqueConstraint(columnNames={"customer_id", "ticket_id"})
    )
public class CustomerRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Override
	public String toString() {
		return "CustomerRequest [ticketId=" + ticketId + ", ticketDescription="
				+ ticketDescription + ", ticketStatus=" + ticketStatus
				+ ", customer=" + customer + ", customerRequestArchived="
				+ customerRequestArchived + "]";
	}

	@Column(name="ticket_id", nullable=false)
	private String ticketId;
	
	@Column(name="ticket_description", nullable=false)
	private String ticketDescription;

	@Column(name="ticket_status", nullable=false)
	private String ticketStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id", referencedColumnName="id", nullable=false)
	private Customer customer;
	
	@Column(name="customer_request_archived")
	private int customerRequestArchived = 0;
	
	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getCustomerRequestArchived() {
		return customerRequestArchived;
	}

	public void setCustomerRequestArchived(int customerRequestArchived) {
		this.customerRequestArchived = customerRequestArchived;
	}
}
