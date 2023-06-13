package com.example.chainsawshoprestbackend.bootstrap;

import com.example.chainsawshoprestbackend.model.Brand;
import com.example.chainsawshoprestbackend.model.Chainsaw;
import com.example.chainsawshoprestbackend.model.Customer;
import com.example.chainsawshoprestbackend.services.BrandService;
import com.example.chainsawshoprestbackend.services.ChainsawService;
import com.example.chainsawshoprestbackend.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitialBootstrap implements CommandLineRunner {

    private final BrandService brandService;
    private final ChainsawService chainsawService;
    private final CustomerService customerService;

    public InitialBootstrap(BrandService brandService, ChainsawService chainsawService, CustomerService customerService) {
        this.brandService = brandService;
        this.chainsawService = chainsawService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        Brand brand1 = Brand.builder().name("First Brand").email("FirstEmail@gmail.com").build();
        Brand brand2 = Brand.builder().name("Second Brand").email("SecondEmail@gmail.com").build();

        Chainsaw woodenChainsaw = Chainsaw.builder().modelName("Wooden").price(100).quantity(100).brand(brand1).build();
        Chainsaw woodenAndMetalChainsaw = Chainsaw.builder().modelName("WoodenAndMetal")
                .price(200).quantity(50).brand(brand1).build();
        Chainsaw metalChainsaw = Chainsaw.builder().modelName("Metal").price(500).quantity(5).brand(brand2).build();

        Customer customer1 = Customer.builder().username("Customer1").firstName("Sasha").lastName("Orlov")
                .email("Alexanderorlovkh@gmail.com").phone("+123782373").password("Root").build();
        Customer customer2 = Customer.builder().username("Customer2").firstName("Denys").lastName("Pysotskiy")
                .email("DenysPysotskiy@gmail.com").phone("+1289172").password("RootRoot").build();

        brand1.getChainsaws().add(woodenChainsaw);
        brand1.getChainsaws().add(woodenAndMetalChainsaw);
        brand2.getChainsaws().add(metalChainsaw);

        brandService.save(brand1);
        brandService.save(brand2);

        chainsawService.save(woodenChainsaw);
        chainsawService.save(woodenAndMetalChainsaw);
        chainsawService.save(metalChainsaw);

        customerService.save(customer1);
        customerService.save(customer2);
    }
}
