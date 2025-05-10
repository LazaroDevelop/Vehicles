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

    @JsonProperty("electrical")
    List<ElectricalRegisterInfo> electricalRegisterInfos;

    @JsonProperty("gasoline")
    List<GasolineRegisterInfo> gasolineRegisterInfos;

    @JsonProperty("diesel")
    List<DieselRegisterInfo> dieselRegisterInfos;
}
