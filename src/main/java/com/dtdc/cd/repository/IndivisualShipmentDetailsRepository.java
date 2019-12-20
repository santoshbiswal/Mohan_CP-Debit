package com.dtdc.cd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.ShipmentBooking;

@Repository
public interface IndivisualShipmentDetailsRepository extends CrudRepository<ShipmentBooking, String>{

	@Query(value="SELECT sb.CONSG_NO,dcb.NAME,dcb.PHONE,sb.VAS_PROD_CODE,sb.BOOKING_PRICE,sb.ITEM_WEIGHT,sb.BOOKING_DATE_TIME,sb.BOOKING_PARCEL_TYPE,sb.QUANTITY,sd.DELIVERY_STATUS,cba.A_LINE1,cba.A_LINE2,cba.CITY,cba.STATE,cba.COUNTRY,cba.ZIP,cba.ADDRESS_TYPE FROM SHIPMENT_BOOKING sb INNER JOIN DTDC_CUSTOMER_BOOKING dcb ON sb.CUST_BOOK_ID=dcb.CUST_BOOK_ID and sb.CONSG_NO=:consignmentnumber INNER JOIN CUSTOMER_BOOKING_ADDRESS cba ON cba.CUST_BOOK_ID=sb.CUST_BOOK_ID INNER JOIN SHIPMENT_DELIVERY sd ON sd.DELIVERY_CONSG_NO=sb.CONSG_NO",nativeQuery = true)
	public List<Object[]> getIndivisualShipmentDetails(@Param("consignmentnumber")String consignmentnumber);
	
}
