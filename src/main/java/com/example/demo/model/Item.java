package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private String name;
    private String imageURL;
    private String author;
    private double price;
    private int quantity;
    private String category;


    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Order order;


    public Item(Book book){
        this.productId= book.getId();
        this.name= book.getName();
        this.imageURL= book.getImageURL();
        this.author= book.getAuthor();
        this.price= book.getPrice();
        this.category = book.getCategory();
    }

}
