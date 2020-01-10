package com.dtdc.cd.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.ShipmentBooking;

@Repository
public interface ShipmentBookingRepository extends CrudRepository<ShipmentBooking, String>{

	@Query(value = "SELECT price FROM DTDC_SERVICE s WHERE s.SOURCE=:source and s.DESTINATION=:destination",nativeQuery = true)
	public Integer findPrice(@Param("source")String source,@Param("destination")String destination);
	
	@Query("FROM ShipmentBooking s WHERE s.bookingDtTime >= :now and s.bookingDtTime < :tomorrow")
	public List<ShipmentBooking> findShipmentBookingByDate(@Param("now") Date now,@Param("tomorrow") Date tomorrow);
	
	@Query(value="select s.CONSG_NO,s.SOURCE,s.DESTINATION,s.BOOKING_DATE_TIME,d.DELIVERY_STATUS,d.REMARKS FROM SHIPMENT_BOOKING s,SHIPMENT_DELIVERY d WHERE s.CONSG_NO=:consignmentNumber and d.DELIVERY_CONSG_NO=:consignmentNumber",nativeQuery = true)
	public List<Object[]> getShipmentAndStatusByShipmentNumber(@Param("consignmentNumber")String consignmentNumber);
	
	@Query(value="select s.CONSG_NO,s.SOURCE,s.DESTINATION,s.BOOKING_DATE_TIME,d.DELIVERY_STATUS,d.REMARKS FROM SHIPMENT_BOOKING s,SHIPMENT_DELIVERY d WHERE s.CONSG_NO=d.DELIVERY_CONSG_NO and s.BOOKING_DATE_TIME >= TO_DATE(:fromDate,'dd/MM/yy') and s.BOOKING_DATE_TIME < TO_DATE(:toDate,'dd/MM/yy') and d.DELIVERY_STATUS=:status",nativeQuery = true)
	public List<Object[]> getShipmentAndStatusBydateRangeAndStatus(@Param("fromDate")String fromDate,@Param("toDate")String toDate,@Param("status")String status);
	
		
	@Query(value="select s.CONSG_NO,s.SOURCE,s.DESTINATION,s.BOOKING_DATE_TIME,d.DELIVERY_STATUS,d.REMARKS FROM SHIPMENT_BOOKING s,SHIPMENT_DELIVERY d WHERE s.CONSG_NO=d.DELIVERY_CONSG_NO and s.BOOKING_DATE_TIME >= TO_DATE(:fromDate,'dd/MM/yy') and s.BOOKING_DATE_TIME < TO_DATE(:toDate,'dd/MM/yy')",nativeQuery = true)
	public List<Object[]> getShipmentAndStatusBydateRange(@Param("fromDate")String fromDate,@Param("toDate")String toDate);
	
	
	@Query(value="select s.CONSG_NO,s.SOURCE,s.DESTINATION,s.BOOKING_DATE_TIME,d.DELIVERY_STATUS,d.REMARKS FROM SHIPMENT_BOOKING s,SHIPMENT_DELIVERY d WHERE s.CONSG_NO=d.DELIVERY_CONSG_NO and s.SOURCE=:source and s.DESTINATION=:destination and d.DELIVERY_STATUS=:status",nativeQuery = true)
	public List<Object[]> getShipmentAndStatusBySourceAndDestAndStatus(@Param("source")String source,@Param("destination")String destination,@Param("status")String status);
	
	@Query(value="select s.CONSG_NO,s.SOURCE,s.DESTINATION,s.BOOKING_DATE_TIME,d.DELIVERY_STATUS,d.REMARKS FROM SHIPMENT_BOOKING s,SHIPMENT_DELIVERY d WHERE s.CONSG_NO=d.DELIVERY_CONSG_NO and s.SOURCE=:source and s.DESTINATION=:destination",nativeQuery = true)
	public List<Object[]> getShipmentAndStatusBySourceAndDest(@Param("source")String source,@Param("destination")String destination);
	
	@Query(value = "select count(sb.CONSG_NO) from SHIPMENT_BOOKING sb where sb.CONSG_NO=:shipmentNumber and sb.VAS_PROD_CODE in('cod','fod','pob')",nativeQuery = true)
	public Integer getBookingCountByShipmentNumber(@Param("shipmentNumber")String shipmentNumber);
}
