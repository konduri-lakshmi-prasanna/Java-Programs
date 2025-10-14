import java.util.*;
import java.util.stream.Collectors;
/**
 * Program Title: Count how many products are in each category
 * Author: [hirushi1999]
 * Date: 2025-10-14
 * Demonstrates how to use Java Streams to perform data analysis on a list of products.
 * Scenario:
 * - A store sells products in various categories (Fruits, Vegetables, Dairy, Meat).
 * - Each product has a price, quantity sold, and availability status.
 * Task:
 * 1. Count how many products are in each category.
 */
class Product {
    private String name;
    private String category;
    private double price;
    private int quantitySold;
    private boolean inStock;

    public Product(String name, String category, double price, int quantitySold, boolean inStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantitySold = quantitySold;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}

public class ProductAnalysis {
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(
                new Product("Apple", "Fruits", 120.0, 50, true),
                new Product("Banana", "Fruits", 80.0, 80, true),
                new Product("Carrot", "Vegetables", 60.0, 70, true),
                new Product("Broccoli", "Vegetables", 90.0, 40, false),
                new Product("Milk", "Dairy", 150.0, 30, true),
                new Product("Cheese", "Dairy", 500.0, 15, true),
                new Product("Yogurt", "Dairy", 200.0, 25, false),
                new Product("Chicken", "Meat", 800.0, 20, true),
                new Product("Beef", "Meat", 1200.0, 10, true),
                new Product("Fish", "Meat", 950.0, 18, false));

        System.out.println("1. Count how many products are in each category");
        // Group products by category and count them
        Map<String, Long> map = productList.stream().collect(Collectors.groupingBy(Product::getCategory,Collectors.counting()));
        // Display results
        map.forEach((cate, count) -> System.out.println(cate + ": " + count));
    }
}
