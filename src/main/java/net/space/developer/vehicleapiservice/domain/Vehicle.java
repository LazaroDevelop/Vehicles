package net.space.developer.vehicleapiservice.domain;

import jakarta.persistence.*;
import lombok.*;
import net.space.developer.vehicleapiservice.enums.VehicleType;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.*;

/**
 * Vehicle entity class to manage vehicle properties
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = VEHICLE_TABLE_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = DISCRIMINATOR_NAME, discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {
    /**
     * Identifier
     */
    @Id
    @Column(name = VEHICLE_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Vehicle registration number
     */
    @Column(name = VEHICLE_REGISTRATION,nullable = false, unique = true)
    private String vehicleRegistration;

    /**
     * Vehicle identification number
     */
    @Column(name = VEHICLE_IDENTIFICATION_NUMBER, nullable = false, unique = true)
    private String vehicleIdentificationNumber;

    /**
     * Define the type of the vehicle
     *
     * @return the vehicle type
     */
    public abstract  VehicleType getVehicleType();
}
