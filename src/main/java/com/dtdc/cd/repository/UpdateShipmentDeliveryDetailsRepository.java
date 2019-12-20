package com.dtdc.cd.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.ShipmentDelivery;

@Repository
public interface UpdateShipmentDeliveryDetailsRepository extends CrudRepository<ShipmentDelivery, Integer>{
	
	@Query(value = "UPDATE SHIPMENT_DELIVERY sd SET sd.DELIVERY_STATUS=:status,sd.DOD=:dod,DELIVERY_PARTNER_ID=:userId WHERE sd.DELIVERY_CONSG_NO=:consignmentnumber", nativeQuery = true)
	public Integer updateDeliveredShipmentStatusById(@Param("status") String status,@Param("dod") Date dod,@Param("userId") Integer userId,@Param("consignmentnumber") String consignmentnumber);
	
}
