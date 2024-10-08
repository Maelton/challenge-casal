package maelton.casal.vehicle_rental_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Vehicle Rental API",
								version = "1.0.0",
								description = "Vehicle Rental API for CASAL's internship application challenge."
								)
)
@SecurityScheme(name = "jwtAuthentication", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@SpringBootApplication
public class VehicleRentalApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(VehicleRentalApiApplication.class, args);
	}
}
