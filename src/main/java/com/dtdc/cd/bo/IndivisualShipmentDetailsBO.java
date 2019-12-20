package com.dtdc.cd.bo;

import java.util.Date;

public class IndivisualShipmentDetailsBO {

	private String consignmentNo;
	private String name;
	private String phone;
	private String vasProdCode;
	private int bookingPrice;
	private float itemWeight;
	private Date bookingDtTime;
	private String bookingParcelType;
	private int quantity;
	private String deliveryStatus;
	private IndivisualShipmentAddressBO fromAddress;
	private IndivisualShipmentAddressBO toAddress;
	
	public IndivisualShipmentAddressBO getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(IndivisualShipmentAddressBO fromAddress) {
		this.fromAddress = fromAddress;
	}
	public IndivisualShipmentAddressBO getToAddress() {
		return toAddress;
	}
	public void setToAddress(IndivisualShipmentAddressBO toAddress) {
		this.toAddress = toAddress;
	}
	public String getConsignmentNo() {
		return consignmentNo;
	}
	public void setConsignmentNo(String consignmentNo) {
		this.consignmentNo = consignmentNo;
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
	public String getVasProdCode() {
		return vasProdCode;
	}
	public void setVasProdCode(String vasProdCode) {
		this.vasProdCode = vasProdCode;
	}
	public int getBookingPrice() {
		return bookingPrice;
	}
	public void setBookingPrice(int bookingPrice) {
		this.bookingPrice = bookingPrice;
	}
	public float getItemWeight() {
		return itemWeight;
	}
	public void setItemWeight(float itemWeight) {
		this.itemWeight = itemWeight;
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
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	@Override
	public String toString() {
		return "IndivisualShipmentDetailsBO [consignmentNo=" + consignmentNo + ", name=" + name + ", phone=" + phone
				+ ", vasProdCode=" + vasProdCode + ", bookingPrice=" + bookingPrice + ", itemWeight=" + itemWeight
				+ ", bookingDtTime=" + bookingDtTime + ", bookingParcelType=" + bookingParcelType + ", quantity="
				+ quantity + ", deliveryStatus=" + deliveryStatus + ", fromAddress=" + fromAddress + ", toAddress="
				+ toAddress + "]";
	}
	
	
}
