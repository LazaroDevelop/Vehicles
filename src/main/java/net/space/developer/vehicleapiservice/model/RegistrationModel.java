package net.space.developer.vehicleapiservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.space.developer.vehicleapiservice.model.diesel.DieselRegisterInfo;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalRegisterInfo;
import net.space.developer.vehicleapiservice.model.gasoline.GasolineRegisterInfo;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

/**
 * Registration model DTO class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegistrationModel extends RepresentationModel<RegistrationModel> {

    /**
     * List representation of the electrical vehicles info
     */
    @JsonProperty("electrical")
    List<ElectricalRegisterInfo> electricalRegisterInfos;

    /**
     * List representation of the gasoline vehicles info
     */
    @JsonProperty("gasoline")
    List<GasolineRegisterInfo> gasolineRegisterInfos;

    /**
     * List representation of the diesel vehicles info
     */
    @JsonProperty("diesel")
    List<DieselRegisterInfo> dieselRegisterInfos;
}
