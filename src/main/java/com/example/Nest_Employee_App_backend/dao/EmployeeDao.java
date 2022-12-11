package com.example.Nest_Employee_App_backend.dao;

import com.example.Nest_Employee_App_backend.model.EmployeeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends CrudRepository<EmployeeModel,Integer> {

    @Query(value = "SELECT `id`, `address`, `dob`, `email`, `name`, `password`, `phone_no` FROM `employees` WHERE `email`=:email AND `password`=:password",nativeQuery = true)
    List<EmployeeModel> LoginVerify(@Param("email") String emailId, @Param("password") String password);


    @Query(value = "SELECT `id`, `address`, `dob`, `email`, `name`,`password`, `phone_no` FROM `employees` WHERE `id`=:id",nativeQuery = true)
    List<EmployeeModel> getDetails(@Param("id") int id);
}
