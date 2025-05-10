package net.space.developer.vehicleapiservice.repository;

import net.space.developer.vehicleapiservice.domain.GasolineVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Gasoline repository class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

@Repository
public interface GasolineRepository extends JpaRepository<GasolineVehicle, Long> {
}
