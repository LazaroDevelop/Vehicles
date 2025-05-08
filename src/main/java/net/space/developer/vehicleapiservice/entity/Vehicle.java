package net.space.developer.vehicleapiservice.entity;

import jakarta.persistence.*;
import lombok.*;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.DISCRIMINATOR_NAME;
import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.VEHICLE_TABLE_NAME;

/**
 * Vehicle entity class to manage vehicle properties
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-08
 */

@Data
@Entity
@Builder
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Vehicle registration number
     */
    @Column(nullable = false, unique = true)
    private String vehicleRegistration;

    /**
     * Vehicle identification number
     */
    @Column(nullable = false, unique = true)
    private String vehicleIdentificationNumber;
}
