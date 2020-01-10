package com.dtdc.cd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.CPDebitLog;

@Repository
public interface ResultStatusRepository extends CrudRepository<CPDebitLog, Integer>{

	@Query(value = "SELECT CDS_NO FROM DTDC_COLLECTION WHERE CONSG_NO=:consignmentnumber",nativeQuery = true)
	public String findCdsByConsgNo(@Param("consignmentnumber") String consignmentnumber);
	
	@Query(value = "INSERT INTO CPDEBIT_LOG (LOGID,DOWNLOAD_FILE_NAME,EXCEL_REMARKS,NODEID,PROCESS_DT_TIME,PROCESS_TYPE,STATUS,STATUS_REMARKS,UPLOAD_FILE_NAME,CDS_NO,LOG_USER_ID,CONSG_NO) VALUES (LOG_SEQUENCE.nextval,:downloadFileName,:excelRemarks,:nodeId,:processDateTime,:processType,:status,:statusRemarks,:uploadFileName,:cdsNo,:logUserId,:consgNo)",nativeQuery = true)
	public Integer saveResultStatus(@Param("downloadFileName") String downloadFileName,@Param("excelRemarks") String excelRemarks,@Param("nodeId") String nodeId,@Param("processDateTime") Date processDateTime,@Param("processType") String processType,@Param("status") String status,@Param("statusRemarks") String statusRemarks,@Param("uploadFileName") String uploadFileName,@Param("cdsNo") String cdsNo,@Param("logUserId") Integer logUserId,@Param("consgNo") String consgNo);
	
	/*@Query(value = "SELECT cl.CONSG_NO,cl.EXCEL_REMARKS,cl.STATUS,cl.STATUS_REMARKS,cl.UPLOAD_FILE_NAME,cl.DOWNLOAD_FILE_NAME,cl.PROCESS_TYPE,cl.PROCESS_DT_TIME,cl.LOG_USER_ID,cl.NODEID FROM CPDEBIT_LOG cl WHERE cl.PROCESS_DT_TIME=:processDateTime",nativeQuery = true)
	public List<BlockShipmentResultBO> getAllCPDebitInfo(@Param("processDateTime") Date processDateTime);*/

//	@Query(value = "from cl.consgNo,cl.excelRemarks,cl.status,cl.statusRemarks,cl.uploadFileName,cl.downloadFileName,cl.processType,cl.processDtTime,cl.logUser,cl.nodeId CPDebitLog cl where cl.processDtTime=:processDateTime" )
	@Query("select cl.consgNo,cl.excelRemarks,cl.status,cl.statusRemarks,cl.uploadFileName,cl.downloadFileName,cl.processType,cl.nodeId from CPDebitLog cl where cl.processDtTime=:processDateTime")
	public List<Object[]> getAllCPDebitInfoByDate(@Param("processDateTime") Date processDateTime);
	
}
