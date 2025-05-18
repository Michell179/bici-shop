package com.usc.repository;

import com.usc.model.Seller;
import java.util.ArrayList;
import java.util.List;

public class SellerRepository {
    private List<Seller> sellers = new ArrayList<>();
    private int nextId = 1;

    public void addSeller(String name) {
        sellers.add(new Seller(nextId++, name));
    }

    public List<Seller> getAllSellers() {
        return sellers;
    }
}
