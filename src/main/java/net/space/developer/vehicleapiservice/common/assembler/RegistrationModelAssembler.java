package net.space.developer.vehicleapiservice.common.assembler;

import net.space.developer.vehicleapiservice.controller.InventoryController;
import net.space.developer.vehicleapiservice.model.RegistrationModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * RegistrationModelAssembler class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Component
public class RegistrationModelAssembler implements RepresentationModelAssembler<RegistrationModel, EntityModel<RegistrationModel>> {

    /**
     * This method converts a RegistrationModel object into an EntityModel object.
     * It adds links to the EntityModel for various actions related to the registration.
     *
     * @param entity The {@link RegistrationModel} object to be converted.
     * @return An EntityModel object containing the registration model and its associated links.
     */
    @Override
    public EntityModel<RegistrationModel> toModel(RegistrationModel entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(InventoryController.class).getVehiclesRegistration()).withSelfRel());
    }
}
