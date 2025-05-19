package com.usc.repository;

import com.usc.model.Buyer;
import java.util.ArrayList;
import java.util.List;

public class BuyerRepository {
    private List<Buyer> buyers = new ArrayList<>();
    private int nextId = 1;

    public void addBuyer(String name) {
        buyers.add(new Buyer(nextId++, name));
    }

    public List<Buyer> getAllBuyers() {
        return buyers;
    }
}
