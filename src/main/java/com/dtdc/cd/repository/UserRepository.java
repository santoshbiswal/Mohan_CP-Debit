package com.dtdc.cd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
