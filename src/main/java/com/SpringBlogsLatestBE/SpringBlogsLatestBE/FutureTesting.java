package com.SpringBlogsLatestBE.SpringBlogsLatestBE;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FutureTesting {
    public void SaveEmployee(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper objectMapper=new ObjectMapper();
       CompletableFuture<Void> completableFuture= CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Employee> employees=objectMapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                    });

                    System.out.println("Thread:"+Thread.currentThread().getName());
                    employees.forEach(System.out::println);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
       completableFuture.get();


    }

    public void saveEmployeeWithLambda(File jsonFile) throws ExecutionException, InterruptedException {
        Executor executor=Executors.newFixedThreadPool(5);
        ObjectMapper objectMapper=new ObjectMapper();
        CompletableFuture<Void> completableFuture= CompletableFuture.runAsync(() -> {
            try {
                List<Employee> employees=objectMapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
                });

                System.out.println("Thread:"+Thread.currentThread().getName());
                //employees.forEach(System.out::println);
                System.out.println(employees.size());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        },executor);
        completableFuture.get();
    }
    public static void main(String args[]) throws ExecutionException, InterruptedException {

        FutureTesting futureTesting=new FutureTesting();
        futureTesting.SaveEmployee(new File("D:\\Java Programmes\\springblogslatestbackend\\src\\main\\java\\com\\SpringBlogsLatestBE\\SpringBlogsLatestBE\\Employee.json"));
        FutureTesting futureTesting1=new FutureTesting();
        futureTesting1.saveEmployeeWithLambda(new File("D:\\\\Java Programmes\\\\springblogslatestbackend\\\\src\\\\main\\\\java\\\\com\\\\SpringBlogsLatestBE\\\\SpringBlogsLatestBE\\\\Employee.json"));


    }
}
