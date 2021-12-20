package com.kevinnt.ezyfoody.datastore;

import com.kevinnt.ezyfoody.models.Order;
import com.kevinnt.ezyfoody.models.Product;

import java.util.ArrayList;

public class DatabaseEntity {

//  Saya menggunakan design pattern singelton
    private static DatabaseEntity instance = null;
    private ArrayList<Product> drinks, snacks, foods, topups;
    private ArrayList<Order> orders;

    private DatabaseEntity() {
        this.drinks = new ArrayList<>();
        this.snacks = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.topups = new ArrayList<>();
        this.orders = new ArrayList<>();
        dummy();
    }

    private void dummy() {
        drinks.add(new Product("Ocha","drink", 2000));
        drinks.add(new Product("Susu Coklat","drink", 4000));
        drinks.add(new Product("Jus Mangga","drink", 8000));
        drinks.add(new Product("Poknari Sweat","drink", 6500));
        drinks.add(new Product("My Zone","drink", 4500));
        drinks.add(new Product("Ice Tea","drink", 2500));
        drinks.add(new Product("Tea Tawar","drink", 500));
        drinks.add(new Product("Marimbak","drink", 1500));
        drinks.add(new Product("Miloh","drink", 3000));
        drinks.add(new Product("C-1500","drink", 7000));

        snacks.add(new Product("Dango","snack", 7000));
        snacks.add(new Product("Oreyo","snack", 4000));
        snacks.add(new Product("Lazy","snack", 6000));
        snacks.add(new Product("Chitata","snack", 4500));
        snacks.add(new Product("Kremezz","snack", 1000));
        snacks.add(new Product("Potabee","snack", 45000));
        snacks.add(new Product("Chicki","snack", 3000));
        snacks.add(new Product("Salut Coki","snack", 1200));
        snacks.add(new Product("Coki Coky","snack", 1500));
        snacks.add(new Product("Astro","snack", 5000));

        foods.add(new Product("Bento","food", 38000));
        foods.add(new Product("Katsu","food", 49000));
        foods.add(new Product("Sushi","food", 5000));
        foods.add(new Product("Rendang","food", 13000));
        foods.add(new Product("Ayam Bakar","food", 14000));
        foods.add(new Product("Soto","food", 17000));
        foods.add(new Product("Nasi Goreng","food", 12000));
        foods.add(new Product("Kare","food", 55000));
        foods.add(new Product("Risoto","food", 24000));
        foods.add(new Product("Sop Iga","food", 45000));

        topups.add(new Product("+ Rp. 10,000","topup", 10100));
        topups.add(new Product("+ Rp. 25,000","topup", 25100));
        topups.add(new Product("+ Rp. 50,000","topup", 50100));
        topups.add(new Product("+ Rp. 100,000","topup", 100100));

    }

    public static DatabaseEntity getInstance(){
        if(instance == null)
            instance = new DatabaseEntity();
        return instance;
    }

    public ArrayList<Product> getDrinks() {
        return drinks;
    }

    public ArrayList<Product> getSnacks() {
        return snacks;
    }

    public ArrayList<Product> getFoods() {
        return foods;
    }

    public ArrayList<Product> getTopups() {
        return topups;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
