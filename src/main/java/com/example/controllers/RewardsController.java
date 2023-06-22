package com.example.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.example.domain.Customer;
import com.example.domain.Transaction;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> addRewards(@RequestBody List<Transaction> transactions) {
        List<Customer> customers = new ArrayList<>();
        
        transactions.stream().forEach(transaction -> {
            int customerId = transaction.getCustomerId();
            
            // if the customer is already in our list retrieve them else create and add
            Customer customer;
            Optional<Customer> optionalCustomer = customers.stream().filter(c -> c.getId() == customerId).findFirst();
            if (optionalCustomer.isPresent()){
                customer = optionalCustomer.get();
            } else {
                customer = new Customer();
                customer.setId(customerId);
                customers.add(customer);
            }

            // Get the month of the transaction
            Calendar cal = Calendar.getInstance();
            cal.setTime(transaction.getDate());
            String month = new SimpleDateFormat("MMM").format(cal.getTime());

            // Get the rewards from the customer record for that month and add to sum else create it
            Map<String, Integer> rewards = customer.getRewards();
            if (rewards.containsKey(month)) {
                int points = rewards.get(month);
                points += calculatePoints(transaction.getAmount());
                rewards.replace(month, points);
            } else {
                int points = calculatePoints(transaction.getAmount());
                rewards.put(month, points);
            }
        });

        return ResponseEntity.ok().body(customers);
    }

    private int calculatePoints(double transactionAmount) {
            int doublePoints = 0;
            int singlePoints = 0;
            
            // 1 point for every dollar spent between $50 and $100
            if (transactionAmount > 50) {
                singlePoints = transactionAmount > 100 ? 50 : (int) transactionAmount - 50;
            }
            // 2 points for every dollar spent over $100
            if (transactionAmount > 100) {
                doublePoints = (int) (transactionAmount - 100) * 2;
            }

            return singlePoints + doublePoints;
    }
}
