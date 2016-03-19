package hack.dev.jigsaw.support_desk.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobileNumber="
				+ mobileNumber + ", telephoneNumber=" + telephoneNumber
				+ ", emailId=" + emailId + ", activeStatus=" + activeStatus
				+ ", tickets=" + tickets + ", customerArchived="
				+ customerArchived + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="name", nullable=false)
	private String name;

	@Column(name="mobile_number", nullable=true)
	private String mobileNumber;

	@Column(name="telephone_number", nullable=true)
	private String telephoneNumber;
	
	@Column(name="email", nullable=true)
	private String emailId;

	@Column(name="active_status", nullable=false)
	private String activeStatus;
	
	@OneToMany(mappedBy="customer")
	private Set<CustomerRequest> tickets;
	
	@Column(name="customer_archived")
	private int customerArchived = 0;

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Set<CustomerRequest> getTickets() {
		return tickets;
	}

	public void setTickets(Set<CustomerRequest> tickets) {
		this.tickets = tickets;
	}

	public int getCustomerArchived() {
		return customerArchived;
	}

	public void setCustomerArchived(int customerArchived) {
		this.customerArchived = customerArchived;
	}
}
