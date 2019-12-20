package com.dtdc.cd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.DtdcCustomerBooking;

@Repository
public interface DtdcCustomerBookingRepository extends CrudRepository<DtdcCustomerBooking, Integer> {

}
