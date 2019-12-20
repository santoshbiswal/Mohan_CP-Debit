package com.dtdc.cd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.CustomerBookingAddress;

@Repository
public interface CustomerBookingAddressRepository extends CrudRepository<CustomerBookingAddress, Integer>{

}
