package com.dtdc.cd.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.DtdcCollection;

@Repository
public interface DtdcCollectionRepository extends CrudRepository<DtdcCollection, Integer>{
	
	@Query(value = "SELECT COUNT(dc.CONSG_NO) FROM DTDC_COLLECTION dc WHERE dc.CONSG_NO=:consignmentnumber AND dc.PRICE IS NOT NULL",nativeQuery = true)
	public Integer getPaymentCount(@Param("consignmentnumber") String consignmentnumber); 
	
	@Query(value = "SELECT COUNT(dc.CONSG_NO) FROM DTDC_COLLECTION dc WHERE dc.CONSG_NO=:consignmentnumber AND dc.CDS_NO IS NOT NULL AND CDS_GENERATED='Y'",nativeQuery = true)
	public Integer getCdsCount(@Param("consignmentnumber") String consignmentnumber);
	
	@Query(value = "SELECT COUNT(dc.CONSG_NO) FROM DTDC_COLLECTION dc WHERE dc.CONSG_NO=:consignmentnumber AND dc.CDS_NO IS NULL AND dc.CDS_GENERATED='Y' AND dc.BLOCK_REMARKS='CP_DEBIT_EXCEL'",nativeQuery = true)
	public Integer getBlockCount(@Param("consignmentnumber") String consignmentnumber);
	
	@Query(value = "UPDATE DTDC_COLLECTION dc SET dc.CDS_GENERATED=:cdsgenerated,dc.EXCEL_REMARKS=:excelremarks,dc.BLOCK_DATE_TIME=:blockdatetime,dc.BLOCK_REMARKS=:blockremarks,dc.BLOCK_USER_ID=:blockuserid WHERE dc.CONSG_NO=:consignmentnumber",nativeQuery = true)
	public void updateBlockShipment(@Param("cdsgenerated") String cdsgenerated,@Param("excelremarks") String excelremarks,@Param("blockdatetime") Date blockdatetime,@Param("blockremarks") String blockremarks,@Param("blockuserid") Integer blockuserid,@Param("consignmentnumber") String consignmentnumber);
}
