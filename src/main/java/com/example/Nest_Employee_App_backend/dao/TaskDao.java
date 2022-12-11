package com.example.Nest_Employee_App_backend.dao;

import com.example.Nest_Employee_App_backend.model.TaskModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TaskDao extends CrudRepository<TaskModel,Integer> {

    @Query(value = "SELECT `id`, `emp_id`, `task_name`, `task_status` FROM `tasks` WHERE `emp_id`=:empId",nativeQuery = true)
    List<TaskModel> userTask(@Param("empId") int empId);



    @Modifying
    @Transactional
    @Query(value = "UPDATE `tasks` SET `task_status`=:taskStatus WHERE `emp_id`=:empId AND `task_name`=:taskName",nativeQuery = true)
    void updateTaskStatus(@Param("empId") int empId,@Param("taskName") String taskName,@Param("taskStatus") String taskStatus);

    @Query(value = "SELECT e.`id`, e.`address`, e.`dob`, e.`email`, e.`name`, e.`phone_no`,t.task_name,t.task_status FROM `employees` e JOIN tasks t ON e.id=t.emp_id",nativeQuery = true)
    List<Map<String,String>> viewAssignedTasks();
}
