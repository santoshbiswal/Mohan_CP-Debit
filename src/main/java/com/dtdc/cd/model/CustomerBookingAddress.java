package com.dtdc.cd.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_BOOKING_ADDRESS")
public class CustomerBookingAddress {

	@Id
	@SequenceGenerator(name = "book_cust_seq", sequenceName = "sequence_cust_add",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_cust_seq")
	@Column(name = "CUST_AID")
	private int custAid;
	
	@Column(name = "A_LINE1")
	private String addressLine1;
	
	@Column(name = "A_LINE2")
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private long zip;
	
	@Column(name = "ADDRESS_TYPE")
	private String addressType;
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUST_BOOK_ID")
	private DtdcCustomerBooking customerBooking;


	public int getCustAid() {
		return custAid;
	}


	public void setCustAid(int custAid) {
		this.custAid = custAid;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public long getZip() {
		return zip;
	}


	public void setZip(long zip) {
		this.zip = zip;
	}


	public String getAddressType() {
		return addressType;
	}


	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}


	public DtdcCustomerBooking getCustomerBooking() {
		return customerBooking;
	}


	public void setCustomerBooking(DtdcCustomerBooking customerBooking) {
		this.customerBooking = customerBooking;
	}
	
	
	
}
