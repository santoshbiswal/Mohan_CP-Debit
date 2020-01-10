package com.dtdc.cd.bo;

public class BlockShipmentBO {

	private String shipmentNumber;
	private String reasonToBlock;
	
	public BlockShipmentBO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlockShipmentBO(String shipmentNumber, String reasonToBlock) {
		super();
		this.shipmentNumber = shipmentNumber;
		this.reasonToBlock = reasonToBlock;
	}
	public String getShipmentNumber() {
		return shipmentNumber;
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}
	public String getReasonToBlock() {
		return reasonToBlock;
	}
	public void setReasonToBlock(String reasonToBlock) {
		this.reasonToBlock = reasonToBlock;
	}
	@Override
	public String toString() {
		return "BlockShipmentBO [shipmentNumber=" + shipmentNumber + ", reasonToBlock=" + reasonToBlock + "]";
	}
	
}
