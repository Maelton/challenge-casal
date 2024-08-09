package maelton.casal.vehicle_rental_api.api.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import maelton.casal.vehicle_rental_api.api.v1.exception.handler.ExceptionResponse;
import maelton.casal.vehicle_rental_api.api.v1.model.dto.rental.RentalRequestDTO;
import maelton.casal.vehicle_rental_api.api.v1.model.dto.rental.RentalResponseDTO;
import maelton.casal.vehicle_rental_api.api.v1.service.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/rentals")
@Tag(name = "Rentals Management", description = "Endpoints for managing rental registries")
public class RentalController {
    @Autowired
    RentalService rentalService;

    //CREATE
    @Operation(summary = "Creates a new rental registry", method = "POST")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "201",
                    description = "New rental registry created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)
                    )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Informed rental id or vehicle id not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)
                    )}
            )
        }
    )
    @PostMapping(produces = "application/json")
    public ResponseEntity<RentalResponseDTO> createRental(@RequestBody RentalRequestDTO rentalCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rentalService.createRental(rentalCreateDTO));
    }

    //READ (ALL)
    @Operation(summary = "Retrieves all rental registries", method = "GET")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",
                    description = "All rental registries returned successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(type="array",
                                    implementation = RentalResponseDTO.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Queried customer email or vehicle chassis not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)
                    )}
            )
        }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<RentalResponseDTO>> getAllRentals(
            @RequestParam(value = "customerEmail", required = false) String customerEmail,
            @RequestParam(value = "vehicleChassis", required = false) String vehicleChassis
    ) {
        return ResponseEntity.ok(rentalService.getAllRentals(customerEmail, vehicleChassis));
    }

    //READ (BY ID)
    @Operation(summary = "Retrieves a rental registry by its id", method = "GET")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",
                    description = "Rental registry returned successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)
                    )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Informed rental UUID does not exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)
                    )}
            )
        }
    )
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<RentalResponseDTO> getRentalById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    //UPDATE
    @Operation(summary = "Updates a rental registry by its id", method = "PUT")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200",
                    description = "Rental registry updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RentalResponseDTO.class)
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid or corrupted request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)
                    )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Informed rental UUID does not exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)
                    )}
            )
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> updateRental(@PathVariable UUID id,
                                                    @RequestBody RentalRequestDTO rentalUpdateDTO) {
        return ResponseEntity.ok(rentalService.updateRental(id, rentalUpdateDTO));
    }

    //DELETE
    @Operation(summary = "Deletes a rental registry by its id", method = "DELETE")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "204",
                    description = "Rental deleted successfully"
            ),
            @ApiResponse(responseCode = "404",
                    description = "Informed rental UUID does not exist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)
                    )}
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable UUID id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}
