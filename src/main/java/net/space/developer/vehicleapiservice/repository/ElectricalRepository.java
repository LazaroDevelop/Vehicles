package net.space.developer.vehicleapiservice.repository;

import net.space.developer.vehicleapiservice.domain.ElectricalVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricalRepository extends JpaRepository<ElectricalVehicle, Long> {
}
