package net.space.developer.vehicleapiservice.repository;

import net.space.developer.vehicleapiservice.domain.ElectricalVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Electrical repository class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

@Repository
public interface ElectricalRepository extends JpaRepository<ElectricalVehicle, Long> {
}
