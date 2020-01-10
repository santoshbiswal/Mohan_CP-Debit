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
@Table(name = "CPDEBIT_LOG")
public class CPDebitLog {

	@Id
	@SequenceGenerator(name = "log_seq",sequenceName = "LOG_SEQUENCE",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "log_seq")
	@Column(name = "LOGID")
	private int logId;
	
	@Column(name = "EXCEL_REMARKS")
	private String excelRemarks;
	private String status;
	
	@Column(name = "STATUS_REMARKS")
	private String statusRemarks;
	
	@Column(name = "UPLOAD_FILE_NAME")
	private String uploadFileName;
	
	@Column(name = "DOWNLOAD_FILE_NAME")
	private String downloadFileName;
	
	@Column(name = "PROCESS_TYPE")
	private String processType;
	
	@Column(name = "PROCESS_DT_TIME")
	private Date processDtTime;
	
	@Column(name = "NODEID")
	private String nodeId;
	
	@Column(name = "CONSG_NO")
	private String consgNo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CDS_NO")
	private DtdcCollection dtdcCollections;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LOG_USER_ID")
	private User logUser;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getExcelRemarks() {
		return excelRemarks;
	}

	public void setExcelRemarks(String excelRemarks) {
		this.excelRemarks = excelRemarks;
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

	public Date getProcessDtTime() {
		return processDtTime;
	}

	public void setProcessDtTime(Date processDtTime) {
		this.processDtTime = processDtTime;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getConsgNo() {
		return consgNo;
	}

	public void setConsgNo(String consgNo) {
		this.consgNo = consgNo;
	}

	public DtdcCollection getDtdcCollections() {
		return dtdcCollections;
	}

	public void setDtdcCollections(DtdcCollection dtdcCollections) {
		this.dtdcCollections = dtdcCollections;
	}

	public User getLogUser() {
		return logUser;
	}

	public void setLogUser(User logUser) {
		this.logUser = logUser;
	}

	@Override
	public String toString() {
		return "CPDebitLog [logId=" + logId + ", excelRemarks=" + excelRemarks + ", status=" + status
				+ ", statusRemarks=" + statusRemarks + ", uploadFileName=" + uploadFileName + ", downloadFileName="
				+ downloadFileName + ", processType=" + processType + ", processDtTime=" + processDtTime + ", nodeId="
				+ nodeId + ", consgNo=" + consgNo + ", dtdcCollections=" + dtdcCollections + ", logUser=" + logUser
				+ "]";
	}

	
}
