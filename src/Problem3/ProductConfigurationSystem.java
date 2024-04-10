package Problem3;

import java.util.*;

//Represents a product
class Product {
 private final String name;
 private final String description;
 private final String category;
 private final double price;

 public Product(String name, String description, String category, double price) {
     this.name = name;
     this.description = description;
     this.category = category;
     this.price = price;
 }

 public String getName() {
     return name;
 }

 public String getDescription() {
     return description;
 }

 public String getCategory() {
     return category;
 }

 public double getPrice() {
     return price;
 }
}

//Represents a product database
class ProductDatabase {
 private final Map<String, List<Product>> categoryMap;
 private final Map<String, Product> productMap;

 public ProductDatabase() {
     categoryMap = new HashMap<>();
     productMap = new HashMap<>();
 }

 // Add a product to the database
 public void addProduct(Product product) {
     String category = product.getCategory();
     productMap.put(product.getName(), product);
     categoryMap.computeIfAbsent(category, k -> new ArrayList<>()).add(product);
 }

 // Search products by category
 public List<Product> searchByCategory(String category) {
     return categoryMap.getOrDefault(category, Collections.emptyList());
 }

 // Search products by keyword
 public List<Product> searchByKeyword(String keyword) {
     List<Product> results = new ArrayList<>();
     for (Product product : productMap.values()) {
         if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
             product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
             results.add(product);
         }
     }
     return results;
 }
}

public class ProductConfigurationSystem {
 public static void main(String[] args) {
     // Create a product database
     ProductDatabase database = new ProductDatabase();

     // Add some products
     database.addProduct(new Product("Laptop", "High-performance laptop", "Electronics", 999.99));
     database.addProduct(new Product("Smartphone", "Latest smartphone model", "Electronics", 599.99));
     database.addProduct(new Product("Book", "Best-selling novel", "Books", 19.99));
     database.addProduct(new Product("Headphones", "Noise-canceling headphones", "Electronics", 149.99));

     // Search products by category
     List<Product> electronicsProducts = database.searchByCategory("Electronics");
     System.out.println("Electronics Products:");
     printProducts(electronicsProducts);

     // Search products by keyword
     List<Product> keywordResults = database.searchByKeyword("smartphone");
     System.out.println("\nKeyword Search Results:");
     printProducts(keywordResults);
 }

 // Helper method to print products
 private static void printProducts(List<Product> products) {
     for (Product product : products) {
         System.out.println("Name: " + product.getName() +
                 ", Description: " + product.getDescription() +
                 ", Category: " + product.getCategory() +
                 ", Price: $" + product.getPrice());
     }
 }
}
