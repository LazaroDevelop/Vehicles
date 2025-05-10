package net.space.developer.vehicleapiservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.space.developer.vehicleapiservice.model.diesel.DieselRegisterInfo;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalRegisterInfo;
import net.space.developer.vehicleapiservice.model.gasoline.GasolineRegisterInfo;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationModel {

    @JsonProperty("electrical")
    List<ElectricalRegisterInfo> electricalRegisterInfos;

    @JsonProperty("gasoline")
    List<GasolineRegisterInfo> gasolineRegisterInfos;

    @JsonProperty("diesel")
    List<DieselRegisterInfo> dieselRegisterInfos;
}
