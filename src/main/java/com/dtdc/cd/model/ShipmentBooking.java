package com.dtdc.cd.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SHIPMENT_BOOKING")
public class ShipmentBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_seq")
	@GenericGenerator(name = "shipment_seq",strategy = "com.dtdc.cd.model.ShipmentNumberCustomKeyGen",
		parameters = {
				@Parameter(name = ShipmentNumberCustomKeyGen.INCREMENT_PARAM, value = "1"),
	            @Parameter(name = ShipmentNumberCustomKeyGen.VALUE_PREFIX_PARAMETER, value = "I"),
	            @Parameter(name = ShipmentNumberCustomKeyGen.NUMBER_FORMAT_PARAMETER, value = "%04d"),
	            @Parameter(name = "sequence_name", value = "shipment_sequence"),
	            @Parameter(name = "initial_value", value = "1")				
		})
	@Column(name = "CONSG_NO")
	private String consg_number;
	
	@Column(name = "VAS_PROD_CODE")
	private String vasProdCode;
	
	@Column(name = "BOOKING_PRICE")
	private int price;
	
	@Column(name = "ITEM_WEIGHT")
	private float itemWeight;
	private String source;
	private String destination;
	
	@Column(name = "BOOKING_DATE_TIME")
	private Date bookingDtTime;
	
	@Column(name = "BOOKING_PARCEL_TYPE")
	private String bookingParcelType;
	private int quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CHANNEL_PARTNER_ID")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUST_BOOK_ID")
	private DtdcCustomerBooking dtdcCustBooking;
	
	@OneToMany(mappedBy = "shipBooking")
	private Set<DtdcCollection> dtdcCollections;

	public Set<DtdcCollection> getDtdcCollections() {
		return dtdcCollections;
	}

	public void setDtdcCollections(Set<DtdcCollection> dtdcCollections) {
		this.dtdcCollections = dtdcCollections;
	}

	public DtdcCustomerBooking getDtdcCustBooking() {
		return dtdcCustBooking;
	}

	public void setDtdcCustBooking(DtdcCustomerBooking dtdcCustBooking) {
		this.dtdcCustBooking = dtdcCustBooking;
	}

	public String getConsg_number() {
		return consg_number;
	}

	public void setConsg_number(String consg_number) {
		this.consg_number = consg_number;
	}

	public String getVasProdCode() {
		return vasProdCode;
	}

	public void setVasProdCode(String vasProdCode) {
		this.vasProdCode = vasProdCode;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public float getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(float itemWeight) {
		this.itemWeight = itemWeight;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getBookingDtTime() {
		return bookingDtTime;
	}

	public void setBookingDtTime(Date bookingDtTime) {
		this.bookingDtTime = bookingDtTime;
	}

	public String getBookingParcelType() {
		return bookingParcelType;
	}

	public void setBookingParcelType(String bookingParcelType) {
		this.bookingParcelType = bookingParcelType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
