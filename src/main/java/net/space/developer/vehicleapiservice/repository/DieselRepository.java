package net.space.developer.vehicleapiservice.repository;

import net.space.developer.vehicleapiservice.domain.DieselVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DieselRepository extends JpaRepository<DieselVehicle, Long> {
}
