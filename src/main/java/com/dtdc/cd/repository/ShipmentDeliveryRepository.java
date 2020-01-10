package com.dtdc.cd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.ShipmentDelivery;

@Repository
public interface ShipmentDeliveryRepository extends CrudRepository<ShipmentDelivery, Integer>{

	@Query(value="select count(sd.DELIVERY_ID) from SHIPMENT_DELIVERY sd where sd.DELIVERY_CONSG_NO=:shipmentNumber and sd.DELIVERY_STATUS='delivered'",nativeQuery = true)
	public Integer getCount(@Param("shipmentNumber")String shipmentNumber);
}
