package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
       
}
