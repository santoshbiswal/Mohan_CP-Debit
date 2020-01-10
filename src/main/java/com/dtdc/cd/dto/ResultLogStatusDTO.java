package com.dtdc.cd.dto;

import java.util.Date;

public class ResultLogStatusDTO {

	private String excelFileRemarks;
	private String uploadFileName;
	private String downloadFileName;
	private String processType;
	private Date processDateTime;
	private int userID;
	private String nodeID;
	
	public String getExcelFileRemarks() {
		return excelFileRemarks;
	}
	public void setExcelFileRemarks(String excelFileRemarks) {
		this.excelFileRemarks = excelFileRemarks;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getDownloadFileName() {
		return downloadFileName;
	}
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public Date getProcessDateTime() {
		return processDateTime;
	}
	public void setProcessDateTime(Date processDateTime) {
		this.processDateTime = processDateTime;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getNodeID() {
		return nodeID;
	}
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	
	
}
