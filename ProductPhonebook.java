 import java.util.HashMap;
 import java.util.Map;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 

 public class ProductPhonebook {
 

  private Map<String, Product> productMap;
 

  public ProductPhonebook() {
  this.productMap = new HashMap<>();
  }
 

  public static class Product {
  private String name;
  private double price;
  private double rating;
 

  public Product(String name, double price, double rating) {
  this.name = name;
  this.price = price;
  this.rating = rating;
  }
 

  public String getName() {
  return name;
  }
 

  public double getPrice() {
  return price;
  }
 

  public double getRating() {
  return rating;
  }
 

  @Override
  public String toString() {
  return "Product{" +
  "name='" + name + '\'' +
  ", price=" + price +
  ", rating=" + rating +
  '}';
  }
  }
 

  public void addProduct(String name, double price, double rating) {
  if (name == null || name.trim().isEmpty()) {
  throw new IllegalArgumentException("Product name cannot be null or empty.");
  }
  if (productMap.containsKey(name)) {
  System.out.println("Product with name '" + name + "' already exists.  Overwriting.");
  }
  productMap.put(name, new Product(name, price, rating));
  }
 

  public Product findProduct(String name) {
  if (name == null || name.trim().isEmpty()) {
  throw new IllegalArgumentException("Product name cannot be null or empty.");
  }
  return productMap.get(name);
  }
 

  public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
  List<Product> results = new ArrayList<>();
  for (Product product : productMap.values()) {
  if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
  results.add(product);
  }
  }
  return results;
  }
 

  public List<Product> searchProductsByRating(double minRating) {
  List<Product> results = new ArrayList<>();
  for (Product product : productMap.values()) {
  if (product.getRating() >= minRating) {
  results.add(product);
  }
  }
  return results;
  }
 

  public boolean removeProduct(String name) {
  if (name == null || name.trim().isEmpty()) {
  throw new IllegalArgumentException("Product name cannot be null or empty.");
  }
  return productMap.remove(name) != null;
  }
 

  public void updateProductPrice(String name, double newPrice) {
  Product product = findProduct(name);
  if (product != null) {
  productMap.put(name, new Product(product.getName(), newPrice, product.getRating()));
  } else {
  System.out.println("Product with name '" + name + "' not found.");
  }
  }
 

  public void updateProductRating(String name, double newRating) {
  Product product = findProduct(name);
  if (product != null) {
  productMap.put(name, new Product(product.getName(), product.getPrice(), newRating));
  } else {
  System.out.println("Product with name '" + name + "' not found.");
  }
  }
 

  public int size() {
  return productMap.size();
  }
 

  public void clear() {
  productMap.clear();
  }
 

  public static void main(String[] args) {
  ProductPhonebook phonebook = new ProductPhonebook();
  Scanner scanner = new Scanner(System.in);
 

  while (true) {
  System.out.println("\nProduct Phonebook Menu:");
  System.out.println("1. Add Product");
  System.out.println("2. Find Product");
  System.out.println("3. Search Products by Price Range");
  System.out.println("4. Search Products by Rating");
  System.out.println("5. Remove Product");
  System.out.println("6. Update Product Price");
  System.out.println("7. Update Product Rating");
  System.out.println("8. View Number of Products");
  System.out.println("9. Clear All Products");
  System.out.println("0. Exit");
  System.out.print("Enter your choice: ");
 

  int choice = scanner.nextInt();
  scanner.nextLine(); // Consume newline
 

  switch (choice) {
  case 1:
  System.out.print("Enter product name: ");
  String name = scanner.nextLine();
  System.out.print("Enter product price: ");
  double price = scanner.nextDouble();
  System.out.print("Enter product rating: ");
  double rating = scanner.nextDouble();
  scanner.nextLine(); // Consume newline
  phonebook.addProduct(name, price, rating);
  System.out.println("Product added.");
  break;
  case 2:
  System.out.print("Enter product name to find: ");
  String searchName = scanner.nextLine();
  Product foundProduct = phonebook.findProduct(searchName);
  if (foundProduct != null) {
  System.out.println("Found product: " + foundProduct);
  } else {
  System.out.println("Product not found.");
  }
  break;
  case 3:
  System.out.print("Enter minimum price: ");
  double minPrice = scanner.nextDouble();
  System.out.print("Enter maximum price: ");
  double maxPrice = scanner.nextDouble();
  scanner.nextLine(); // Consume newline
  List<Product> priceRangeProducts = phonebook.searchProductsByPriceRange(minPrice, maxPrice);
  System.out.println("Products in price range $" + minPrice + " - $" + maxPrice + ":");
  for (Product product : priceRangeProducts) {
  System.out.println(product);
  }
  break;
  case 4:
  System.out.print("Enter minimum rating: ");
  double minRating = scanner.nextDouble();
  scanner.nextLine(); // Consume newline
  List<Product> ratingProducts = phonebook.searchProductsByRating(minRating);
  System.out.println("Products with rating " + minRating + " or higher:");
  for (Product product : ratingProducts) {
  System.out.println(product);
  }
  break;
  case 5:
  System.out.print("Enter product name to remove: ");
  String removeName = scanner.nextLine();
  boolean removed = phonebook.removeProduct(removeName);
  if (removed) {
  System.out.println("Product '" + removeName + "' removed.");
  } else {
  System.out.println("Product '" + removeName + "' not found.");
  }
  break;
  case 6:
  System.out.print("Enter product name to update price: ");
  String updatePriceName = scanner.nextLine();
  System.out.print("Enter new price: ");
  double newPrice = scanner.nextDouble();
  scanner.nextLine(); // Consume newline
  phonebook.updateProductPrice(updatePriceName, newPrice);
  System.out.println("Price updated.");
  break;
  case 7:
  System.out.print("Enter product name to update rating: ");
  String updateRatingName = scanner.nextLine();
  System.out.print("Enter new rating: ");
  double newRating = scanner.nextDouble();
  scanner.nextLine(); // Consume newline
  phonebook.updateProductRating(updateRatingName, newRating);
  System.out.println("Rating updated.");
  break;
  case 8:
  System.out.println("Number of products: " + phonebook.size());
  break;
  case 9:
  phonebook.clear();
  System.out.println("All products cleared.");
  break;
  case 0:
  System.out.println("Exiting...");
  scanner.close();
  return;
  default:
  System.out.println("Invalid choice. Please try again.");
  }
  }
  }
 }
 