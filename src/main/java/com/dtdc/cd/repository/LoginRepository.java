package com.dtdc.cd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dtdc.cd.model.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer>{

	@Query("select l.loginId from Login l where l.username=:username and l.password=:password and l.usertype=:usertype")
	public Integer getLoginCount(@Param("username")String username,@Param("password")String password,@Param("usertype")String usertype);
}
