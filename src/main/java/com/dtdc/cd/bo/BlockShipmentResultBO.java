package com.dtdc.cd.bo;

public class BlockShipmentResultBO {

	private String shipmentNumber;
	private String excelFileRemarks;
	private String status;
	private String statusRemarks;
	private String uploadFileName;
	private String downldFileName;
	private String processType;
	//private Date processDateTime;
	//private int userID;
	private String nodeID;

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getExcelFileRemarks() {
		return excelFileRemarks;
	}

	public void setExcelFileRemarks(String excelFileRemarks) {
		this.excelFileRemarks = excelFileRemarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusRemarks() {
		return statusRemarks;
	}

	public void setStatusRemarks(String statusRemarks) {
		this.statusRemarks = statusRemarks;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getDownldFileName() {
		return downldFileName;
	}

	public void setDownldFileName(String downldFileName) {
		this.downldFileName = downldFileName;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	@Override
	public String toString() {
		return "BlockShipmentResultBO [shipmentNumber=" + shipmentNumber + ", excelFileRemarks=" + excelFileRemarks
				+ ", status=" + status + ", statusRemarks=" + statusRemarks + ", uploadFileName=" + uploadFileName
				+ ", downldFileName=" + downldFileName + ", processType=" + processType + ", nodeID=" + nodeID + "]";
	}
	
}
