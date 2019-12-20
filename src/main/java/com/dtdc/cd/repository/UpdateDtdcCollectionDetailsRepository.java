package com.dtdc.cd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.DtdcCollection;

@Repository
public interface UpdateDtdcCollectionDetailsRepository extends CrudRepository<DtdcCollection, Integer>{
	
}
