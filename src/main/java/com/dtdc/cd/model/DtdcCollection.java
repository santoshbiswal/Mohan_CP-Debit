package com.dtdc.cd.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DTDC_COLLECTION")
public class DtdcCollection {

	@Id
	@SequenceGenerator(name="COLLECTION_SEQ",sequenceName = "SEQ_DTDC_COLLECTION",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COLLECTION_SEQ")
	@Column(name = "COLLECTION_ID")
	private int collectionId;
	
	@Column(name = "CDS_NO")
	private UUID cdsNo;
	
	@Column(name = "CDS_DATE_TIME")
	private Date cdsDateTime;
	private int price;
	
	@Column(name = "CDS_GENERATED")
	private char cdsGenerated;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CONSG_NO")
	private ShipmentBooking shipBooking;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COLLECTION_MEMBER_ID")
	private User collectionUser;
	
	@OneToMany(mappedBy = "dtdcCollections")
	private Set<CPDebitLog> cpDebitLogs;

	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	public UUID getCdsNo() {
		return cdsNo;
	}
	public void setCdsNo(UUID cdsNo) {
		this.cdsNo = cdsNo;
	}
	public Date getCdsDateTime() {
		return cdsDateTime;
	}
	public void setCdsDateTime(Date cdsDateTime) {
		this.cdsDateTime = cdsDateTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ShipmentBooking getShipBooking() {
		return shipBooking;
	}
	public void setShipBooking(ShipmentBooking shipBooking) {
		this.shipBooking = shipBooking;
	}
	public User getCollectionUser() {
		return collectionUser;
	}
	public void setCollectionUser(User collectionUser) {
		this.collectionUser = collectionUser;
	}
	public char getCdsGenerated() {
		return cdsGenerated;
	}
	public void setCdsGenerated(char cdsGenerated) {
		this.cdsGenerated = cdsGenerated;
	}
	public Set<CPDebitLog> getCpDebitLogs() {
		return cpDebitLogs;
	}
	public void setCpDebitLogs(Set<CPDebitLog> cpDebitLogs) {
		this.cpDebitLogs = cpDebitLogs;
	}
	@PrePersist
	protected void onCreate() { 		
		// set the uid String
		setCdsNo(java.util.UUID.randomUUID());
	}
	
}
