package com.dtdc.cd.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DTDC_CUSTOMER_BOOKING")
public class DtdcCustomerBooking {

	@Id
	@SequenceGenerator(name = "seq_customer",sequenceName = "customer_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_customer")
	@Column(name = "CUST_BOOK_ID")
	private int custBookID;
	private String name;
	private String phone;
	private String email;
	
	@OneToOne(mappedBy = "dtdcCustBooking")
	private ShipmentBooking shipBooking;
	
	@OneToMany(mappedBy = "customerBooking")
	private Set<CustomerBookingAddress> bookingaddresses;
	
	public Set<CustomerBookingAddress> getBookingaddresses() {
		return bookingaddresses;
	}
	public void setBookingaddresses(Set<CustomerBookingAddress> bookingaddresses) {
		this.bookingaddresses = bookingaddresses;
	}
	public ShipmentBooking getShipBooking() {
		return shipBooking;
	}
	public void setShipBooking(ShipmentBooking shipBooking) {
		this.shipBooking = shipBooking;
	}
	public int getCustBookID() {
		return custBookID;
	}
	public void setCustBookID(int custBookID) {
		this.custBookID = custBookID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
