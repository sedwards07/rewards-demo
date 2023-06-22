package com.example.domain;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    public int id;
    public Map<String, Integer> rewards = new HashMap<String,Integer>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Integer> getRewards() {
        return rewards;
    }

    public void setRewards(Map<String, Integer> rewards) {
        this.rewards = rewards;
    }
}
