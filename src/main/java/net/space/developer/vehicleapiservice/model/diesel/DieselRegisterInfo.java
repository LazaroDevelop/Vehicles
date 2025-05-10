package net.space.developer.vehicleapiservice.model.diesel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.space.developer.vehicleapiservice.enums.diesel.InjectionType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DieselRegisterInfo {

    @JsonProperty("registration")
    private String vehicleRegistration;
    private InjectionType pumpType;

}
