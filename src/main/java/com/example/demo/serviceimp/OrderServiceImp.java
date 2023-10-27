package com.example.demo.serviceimp;

import com.example.demo.Repository.OrderRepository;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImp implements OrderService {


    @Autowired
    private OrderRepository orderRepository;
    private Set<Integer> orderList = new HashSet<Integer>();
    private Set<Item> booklist = new HashSet<>();




    public void Add(int id) {
        this.orderList.add(id);
    }

    public void Delete(String id) {
        this.orderList.removeIf(integer -> integer==Integer.parseInt(id));
        this.booklist.removeIf(item -> item.getProductId()==Integer.parseInt(id));

    }
    public void Clear() {
        this.orderList.clear();
        this.booklist.clear();
    }

    public void Save(Order order){
        this.orderRepository.save(order);

    }

    @Override
    public Set<Item> getBooklist() {
        return this.booklist;
    }

    @Override
    public Set<Integer> getOrderList() {
        return this.orderList;
    }
}
