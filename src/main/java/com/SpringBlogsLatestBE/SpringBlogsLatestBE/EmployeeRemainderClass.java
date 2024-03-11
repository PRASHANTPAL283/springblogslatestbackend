package com.SpringBlogsLatestBE.SpringBlogsLatestBE;

import com.SpringBlogsLatestBE.SpringBlogsLatestBE.Services.EmployeeServices;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
//then appply single thread
//then applyasybnc multimple
public class EmployeeRemainderClass {
    @Autowired
    public  EmployeeServices employeeServices;

    public  CompletableFuture<Void> sendRemainderMail(){

             CompletableFuture<Void> completableFuture=   CompletableFuture.supplyAsync(()->{
                    System.out.println("Thread Name1:"+Thread.currentThread().getName());
                    File file=new File("D:\\Java Programmes\\springblogslatestbackend\\src\\main\\java\\com\\SpringBlogsLatestBE\\SpringBlogsLatestBE\\Employee.json");
                    List<Employee> employeeList= null;
                    try {
                        employeeList = getallEmployees(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                        return employeeList;

                }).thenApplyAsync(employees -> {
                    System.out.println("Thread Name2:"+Thread.currentThread().getName());
                    return employees.stream().filter(e->e.getNewJoiner().equals("TRUE")).collect(Collectors.toList());
                }).thenApplyAsync(employees -> {
                    System.out.println("Thread Name3:"+Thread.currentThread().getName());
                    return employees.stream().filter(e->e.getLearningPending().equals("TRUE")).collect(Collectors.toList());
                }).thenApplyAsync(employees -> {
                    System.out.println("thread name 4:"+Thread.currentThread().getName());
                    return employees.stream().map(Employee::getEmail).collect(Collectors.toList());
                }).thenAcceptAsync(email->{
                    System.out.println("thread name 5:"+Thread.currentThread().getName());
                    email.stream().forEach(e->{
                        sendEmail(e);
                    });
                });
             return completableFuture;
    }
    public  void sendEmail(String mail){
        System.out.println("the sending training mail to employee "+mail);
    }

    public List<Employee> getallEmployees(File jsonFile) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Employee> employees = objectMapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
            });

            return employees;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public static void main (String args[])throws Exception{
        EmployeeRemainderClass remainderClass=new EmployeeRemainderClass();
        remainderClass.sendRemainderMail().get();


    }
}
