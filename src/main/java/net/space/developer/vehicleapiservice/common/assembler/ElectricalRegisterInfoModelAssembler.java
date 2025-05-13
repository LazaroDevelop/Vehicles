package net.space.developer.vehicleapiservice.common.assembler;

import net.space.developer.vehicleapiservice.controller.InventoryController;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.model.ConversionModel;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalRegisterInfo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * ElectricalRegisterInfoModelAssembler class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Component
public class ElectricalRegisterInfoModelAssembler implements RepresentationModelAssembler<ElectricalRegisterInfo, EntityModel<ElectricalRegisterInfo>> {

    /**
     * This method converts an {@link ElectricalRegisterInfo} object into an EntityModel object.
     *
     * @param registerInfo The RegistrationModel object to be converted.
     * @return An EntityModel object containing the registration model and its associated links.
     */
    @Override
    public EntityModel<ElectricalRegisterInfo> toModel(ElectricalRegisterInfo registerInfo) {
        return EntityModel.of(registerInfo,
                linkTo(methodOn(InventoryController.class).convertVehicle(1L, null)).withSelfRel());
    }
}
