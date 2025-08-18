import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8LTS {
    public static void main(String[] args) {
        functionalInterfaces();
        streams();
        optional();
    }

    public static void optional() {
        System.out.println("------Optional---------");
        Optional<String> emptyOptional = Optional.empty();
        Optional<String> valueOptional = Optional.of("Value");
//        THIS can't be done "NullPointerException"
//        Optional<String> valueNullOptional = Optional.of(null);
        Optional<String> nullableOptional = Optional.ofNullable(null);
        Optional<String> valueNullableOptional = Optional.ofNullable("Nullable");
        System.out.println(emptyOptional);
//        System.out.println(emptyOptional.get());
        System.out.println(valueOptional);
        System.out.println(valueOptional.get());
        System.out.println(nullableOptional);
//        System.out.println(nullableOptional.get());
        System.out.println(valueNullableOptional);
        System.out.println(valueNullableOptional.get());
    }

    public static void streams() {
        System.out.println("------Stream---------");
        System.out.println(wordCountStream("zied yazidi sami yazidi yasmine yazidi"));

        ProductCRUD productCRUD = new ProductCRUD();
        ProductCRUD.createProduct(1, "Laptop", 1200.00);
        ProductCRUD.createProduct(2, "Phone", 800.00);
        ProductCRUD.createProduct(3, "Pen", 2.50);
        ProductCRUD.createProduct(4, "Notebook", 5.00);
        ProductCRUD.createProduct(5, "Headphones", 150.00);
        productCRUD.filterAndSortAndPrint(3.00);
    }

    public static void functionalInterfaces() {
        IntFunction square = a -> a * a;
        System.out.println("------IntFunction------");
        System.out.println(square.apply(4));

        Function<List<Integer>, List<Integer>> sortFunction = unsortedList -> unsortedList.stream().sorted().collect(Collectors.toList());
        System.out.println("------Function---------");
        System.out.println(sortFunction.apply(Stream.of(4, 2, 1, 3).collect(Collectors.toList())));

        Predicate<Integer> isAdult = age -> age >= 18;
        System.out.println("------Predicate---------");
        System.out.println(isAdult.test(18));

        Consumer<String> sayHello = name -> System.out.println(new StringBuilder("Hello ").append(name));
        System.out.println("------Consumer---------");
        sayHello.accept("Consumer");

        Supplier<String> supplier = () -> "Hello Predicate";
        System.out.println("------Predicate---------");
        System.out.println(supplier.get());
    }


    public static Map<String, Long> wordCountStream(String text) {
        return Stream.of(text.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    static class UserRepository {
        private Optional<User> findUserById(List<User> users, Long id) {
            return users.stream()
                    .filter(user -> id.equals(user.getId()))
                    .findFirst();
        }

        private String findUserEmailById(List<User> users, Long id) {
            return findUserById(users, id)
                    .flatMap(User::getEmail)
                    .map(String::toUpperCase)
                    .orElse("default@email.com");
        }

        private Optional<String> getUserAddress(List<User> users, Long id) {
            return findUserById(users, id)
                    .flatMap(User::getAddress);
        }

        private void validateUserAge(List<User> users, Long id) {
            findUserById(users, id)
                    .flatMap(User::getAge)
                    .ifPresent(age -> {
                        if (age >= 18) {
                            System.out.println("User is adult");
                        } else {
                            System.out.println("User is minor");
                        }
                    });
        }
    }

    static class User {
        private Long id;
        private String name;
        private String email;
        private Integer age;
        private String address;

        public User(Long id, String name, String email, Integer age, String address) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
            this.address = address;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Optional<String> getEmail() {
            return Optional.ofNullable(email);
        }

        public Optional<Integer> getAge() {
            return Optional.ofNullable(age);
        }

        public Optional<String> getAddress() {
            return Optional.ofNullable(address);
        }
    }

    static class Product {
        private int id;
        private String name;
        private double price;

        // Constructor
        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }

    static class Transaction {
        private int transactionId;
        private int productId;
        private int quantity;
        private double totalPrice;

        public Transaction(int transactionId, int productId, int quantity, double totalPrice) {
            this.transactionId = transactionId;
            this.productId = productId;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
        }

        public int getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotalPrice() {
            return totalPrice;
        }
    }

    static class ProductCRUD {
        private static List<Product> products = new ArrayList<>();
        private static List<Transaction> transactions = new ArrayList<>();

        public static void createProduct(int id, String name, double price) {
            Product product = new Product(1, "PC", 1500d);
            products.add(product);
        }

        // Read - print all products
        public static void printAllProducts() {
            products.forEach(System.out::println);
        }

        // Read - Find a product by ID
        public static Product getProductById(int id) {
            return products.stream()
                    .filter(product -> product.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        // Update - Update a product's details
        public static void updateProduct(int id, String newName, double newPrice) {
            products.stream()
                    .filter(product -> product.getId() == id)
                    .findFirst()
                    .ifPresent(product -> {
                        product.setName(newName);
                        product.setPrice(newPrice);
                    });
        }

        // Delete - Remove a product by ID
        public static void deleteProduct(int id) {
            products.removeIf(product -> product.getId() == id);
        }


        public void filterAndSortAndPrint(double limitPrice) {
            products.stream()
                    .filter(product -> product.getId() > limitPrice)
                    .sorted(Comparator.comparing(Product::getName))
                    .map(Product::getName)
                    .forEach(System.out::println);
        }

        public Map<String, Double> groupAndAverage() {
            return products.stream()
                    .collect(Collectors.groupingBy(
                            Product::getName,
                            Collectors.averagingDouble(Product::getPrice)));
        }


        public void totalRevenueByProduct() {
//            Get all transaction by product id
            Map<Integer, Double> productSum = transactions.stream()
                    .collect(Collectors.groupingBy(
                            Transaction::getProductId,
                            Collectors.summingDouble(Transaction::getTotalPrice)));

            productSum.forEach((productId, totalRevenue) -> {
                String productName = products.stream()
                        .filter(product -> product.getId() == productId)
                        .map(Product::getName)
                        .findFirst()
                        .orElse("Unknown Product");
                System.out.println(productName + ": " + totalRevenue);
            });
        }

        public void mostSoldProductByQuantity() {
            Map<Integer, Integer> res = transactions.stream()
                    .collect(Collectors.groupingBy(
                            Transaction::getProductId,
                            Collectors.summingInt(Transaction::getQuantity)
                    ));
            Optional<Map.Entry<Integer, Integer>> max = res.entrySet().stream()
                    .max(Map.Entry.comparingByValue());
            if (max.isPresent()) {
                Integer productId = max.get().getKey();
                Integer quantity = max.get().getValue();
                String prodcutName = products.stream()
                        .filter(product -> product.getId() == productId)
                        .map(Product::getName)
                        .findFirst()
                        .orElse("UNKNOWW product");
                System.out.println(prodcutName + ": " + quantity);
            }
        }
    }
}
