package maelton.casal.vehicle_rental_api.api.v1.model.dto.vehicle.car;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for creating or updating cars")
public record CarRequestDTO(String model, String chassis, int numberOfDoors) {
}
