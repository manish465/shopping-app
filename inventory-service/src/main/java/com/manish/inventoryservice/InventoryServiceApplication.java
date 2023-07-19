package com.manish.inventoryservice;

import com.manish.inventoryservice.model.Inventory;
import com.manish.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			inventoryRepository.deleteAll();

			Inventory inventory = new Inventory();
			inventory.setSkuCode("Iphone_13");
			inventory.setQuantity(100);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Iphone_13_gold");
			inventory1.setQuantity(0);

			Inventory inventory2 = new Inventory();
			inventory1.setSkuCode("Iphone_13_red");
			inventory1.setQuantity(200);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}

}
