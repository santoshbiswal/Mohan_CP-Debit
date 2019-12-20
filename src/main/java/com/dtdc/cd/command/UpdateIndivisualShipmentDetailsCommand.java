package com.dtdc.cd.command;

public class UpdateIndivisualShipmentDetailsCommand {

	private String consignmentNo;
	private int price;
	private String deliveryStatus;
	private int userId;
	
	public String getConsignmentNo() {
		return consignmentNo;
	}
	public void setConsignmentNo(String consignmentNo) {
		this.consignmentNo = consignmentNo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UpdateIndivisualShipmentDetailsCommand [consignmentNo=" + consignmentNo + ", price=" + price
				+ ", deliveryStatus=" + deliveryStatus + ", userId=" + userId + "]";
	}
	
	
}
