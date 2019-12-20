package com.dtdc.cd.model;

import java.util.Date;

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
@Table(name = "SHIPMENT_DELIVERY")
public class ShipmentDelivery {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="delivery_sequence" )
	@SequenceGenerator(name = "delivery_sequence", sequenceName = "SHIPMENT_DELIVERY_SEQUENCE", allocationSize = 1)
	@Column(name = "DELIVERY_ID")
	private int deliveryId;
	
	//shipment number present in Booking table
	@Column(name = "DELIVERY_CONSG_NO")
	private String deliveryConsgNo;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="DELIVERY_PARTNER_ID")
	private User deliveryUser;
	
	@Column(name = "DELIVERY_STATUS")
	private String deliveryStatus;
	
	private Date dod;
	
	private String remarks;

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getDeliveryConsgNo() {
		return deliveryConsgNo;
	}

	public void setDeliveryConsgNo(String deliveryConsgNo) {
		this.deliveryConsgNo = deliveryConsgNo;
	}

	public User getDeliveryUser() {
		return deliveryUser;
	}

	public void setDeliveryUser(User deliveryUser) {
		this.deliveryUser = deliveryUser;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getDod() {
		return dod;
	}

	public void setDod(Date dod) {
		this.dod = dod;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
