package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services;


import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Dao.EmployeeDao;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Employee;
import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities.EmployeeEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServices {

    @Autowired
    public EmployeeDao employeeDao;

    public List<EmployeeEntity> addAllEmployees() throws IOException {

        File file=new File("D:\\Java Programmes\\springblogslatestbackend\\src\\main\\java\\com\\SpringBlogsLatestBE\\SpringBlogsLatestBE\\Employee.json");
        ObjectMapper objectMapper=new ObjectMapper();
        List<EmployeeEntity> employeeEntities=objectMapper.readValue(file, new TypeReference<List<EmployeeEntity>>() {
        });

        return this.employeeDao.saveAll(employeeEntities);


    }

    public List<Employee> getallEmployees(){
        List<EmployeeEntity> list= this.employeeDao.findAll();
        List<Employee> employees=new ArrayList<>();
        list.stream().forEach(e->{
            Employee employee=new Employee();
            BeanUtils.copyProperties(e,employee);
            employees.add(employee);
        });

        return employees;

    }
}
