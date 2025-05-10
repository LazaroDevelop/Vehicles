package net.space.developer.vehicleapiservice.common.assembler;

import jakarta.validation.constraints.NotNull;
import net.space.developer.vehicleapiservice.controller.InventoryController;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * VehicleModelAssembler class
 * This class is responsible for assembling vehicle models.
 * It is currently empty and serves as a placeholder for future implementations.
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Component
public class VehicleModelAssembler implements RepresentationModelAssembler<VehicleModel, EntityModel<VehicleModel>> {

    /**
     * This method converts a VehicleModel object into an EntityModel object.
     * It adds links to the EntityModel for various actions related to the vehicle.
     *
     * @param vehicleModel The VehicleModel object to be converted.
     * @return An EntityModel object containing the vehicle model and its associated links.
     */
    @Override
    public EntityModel<VehicleModel> toModel(@NotNull VehicleModel vehicleModel) {
        return EntityModel.of(vehicleModel,
                linkTo(methodOn(InventoryController.class).getVehicleById(vehicleModel.getId())).withSelfRel(),
                linkTo(methodOn(InventoryController.class).createVehicle(vehicleModel)).withRel("create"),
                linkTo(methodOn(InventoryController.class).getAllVehicles()).withRel("all"),
                linkTo(methodOn(InventoryController.class).getAllPaginated(0, 10, new ArrayList<>())).withRel("all_paged"),
                linkTo(methodOn(InventoryController.class).getAllByVehicleType(vehicleModel.getVehicleType().name())).withRel("by_vehicle_type"),
                linkTo(methodOn(InventoryController.class).getAllByVehicleTypePaginated(vehicleModel.getVehicleType().name(), 0, 10, new ArrayList<>())).withRel("by_vehicle_type_paged"),
                linkTo(methodOn(InventoryController.class).updateVehicle(vehicleModel.getId(), vehicleModel)).withRel("update"),
                linkTo(methodOn(InventoryController.class).deleteVehicle(vehicleModel.getId())).withRel("delete")
        );
    }
}
