package com.example.Nest_Employee_App_backend.controller;

import com.example.Nest_Employee_App_backend.dao.EmployeeDao;
import com.example.Nest_Employee_App_backend.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao daoEmployee;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addEmployee",produces = "application/json",consumes = "application/json")
    public HashMap<String,String> EmployeeRegistration(@RequestBody EmployeeModel e)
    {

        daoEmployee.save(e);

        HashMap<String,String> map=new HashMap<>();

        map.put("status","success");

        return map;

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/login",produces = "application/json",consumes = "application/json")
    public HashMap<String,String> EmployeeLogin(@RequestBody EmployeeModel e)
    {

//        String email=String.valueOf(e.getEmail().toString());
//        String password=String.valueOf(e.getPassword().toString());

        List<EmployeeModel> result= daoEmployee.LoginVerify(e.getEmail(),e.getPassword());

        HashMap<String,String> map=new HashMap<>();

        if(result.size()==0)
        {
            map.put("status","Invalid");
        }
        else
        {
            map.put("status","success");
            map.put("id",String.valueOf(result.get(0).getId()));
        }

        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/empdetails",produces = "application/json",consumes = "application/json")
    public List<EmployeeModel> getDetails(@RequestBody EmployeeModel e)
    {

        return (List<EmployeeModel>) daoEmployee.getDetails(e.getId());
    }


}
