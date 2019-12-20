package com.dtdc.cd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{

}
