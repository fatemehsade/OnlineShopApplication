package com.example.onlineshopapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.onlineshopapplication.model.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Insert
    void insert(Customer customer);

    @Query("select * from customer_table")
    List<Customer> getCustomers();
}
