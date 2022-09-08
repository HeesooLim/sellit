package com.project.sellit.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.List;

@Entity(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int username;
    private int buyerId;
    private String title;
    private Double price;
    private String description;
    private String location;
    private Date date;
    private String status;
    private String favUsernames;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return username;
    }

    public void setUserId(int username) {
        this.username = username;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFavouriteIds() {
        return favUsernames;
    }

    public void setFavouriteIds(String favUsernames) {
        this.favUsernames = favUsernames;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", username=" + username +
                ", buyerId=" + buyerId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", locaton='" + location + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", favouriteIds='" + favUsernames + '\'' +
                '}';
    }
}
