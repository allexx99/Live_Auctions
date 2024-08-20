package com.example.Live.Auctions;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition (
		info = @Info(
				title = "Live Auctions Project",
				version = "1.0.0",
				description = "PS class project"
		)
)
public class LiveAuctionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveAuctionsApplication.class, args);
	}

}
