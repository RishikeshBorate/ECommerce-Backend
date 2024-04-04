package org.project.productservice;

import org.project.productservice.models.Category;
import org.project.productservice.models.Price;
import org.project.productservice.models.Product;
import org.project.productservice.repositories.CategoryRepository;
import org.project.productservice.repositories.PriceRepository;
import org.project.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public ProductServiceApplication(ProductRepository productRepository,
                                     CategoryRepository categoryRepository,
                                     PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);


    }
    @Override
    public void run(String... args) throws Exception {
        /*Category category1 = new Category() ;
        category1.setName("Apple Devices");
        Category savedCategory1 = categoryRepository.save(category1) ;

        Price price1  = new Price() ;
        price1.setCurrency("INR");
        price1.setValue(150000L);
       // Price savedPrice = priceRepository.save(price1) ;

        Product product1 = new Product() ;
        product1.setCategory(savedCategory1);
        product1.setDescription("Best iphone ever");
        product1.setTitle("iPhone15");
        product1.setPrice(price1);


        Price price2  = new Price() ;
        price2.setCurrency("USD");
        price2.setValue(50000L);

        Product product2 = new Product() ;
        product2.setCategory(savedCategory1);
        product2.setDescription("Best iphone ever");
        product2.setTitle("iPhone15 Pro");
        product2.setPrice(price2);

        Price price3  = new Price() ;
        price3.setCurrency("INR");
        price3.setValue(85000L);

        Product product3 = new Product() ;
        product3.setCategory(savedCategory1);
        product3.setDescription("Best iphone ever");
        product3.setTitle("iPhone12 Pro");
        product3.setPrice(price3);


        Category category2 = new Category() ;
        category2.setName("Mac");
        Category savedCategory2 = categoryRepository.save(category2) ;


        Price price4  = new Price() ;
        price4.setCurrency("USD");
        price4.setValue(2500000L);

        Product product4 = new Product() ;
        product4.setCategory(savedCategory2);
        product4.setDescription("Fastest Mac");
        product4.setTitle("Macbook Air 15");
        product4.setPrice(price4);

        productRepository.save(product1);
        productRepository.save(product2) ;
        productRepository.save(product3) ;
        productRepository.save(product4) ;

        /*
        // System.out.println(product1);


        //Optional<Category> categoryOptinal = categoryRepository.findById(UUID.fromString("ebda9b7b-a5df-449e-bc59-978d5b183716")) ;
       // List<Product> products =productRepository.getByCategory(category) ;

        for(Product product : products){
            System.out.println(product.getTitle());
        }
       // Category category = categoryOptinal.get() ;
        //System.out.println(category.getName());

       // List<Product> products = category.getProductList() ;

        //for(Product product : products){
        //    System.out.println(product.getTitle());
        //}
       */
    }
}
