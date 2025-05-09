package net.space.developer.vehicleapiservice.repository;

import net.space.developer.vehicleapiservice.domain.GasolineVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasolineRepository extends JpaRepository<GasolineVehicle, Long> {
}
