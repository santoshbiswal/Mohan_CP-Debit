package com.dtdc.cd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.ShipmentDelivery;

@Repository
public interface ShipmentDeliveryRepository extends CrudRepository<ShipmentDelivery, Integer>{

}
