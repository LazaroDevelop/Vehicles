package net.space.developer.vehicleapiservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.space.developer.vehicleapiservice.common.exceptions.VehicleAlreadyRegisteredException;
import net.space.developer.vehicleapiservice.common.exceptions.VehicleInvalidException;
import net.space.developer.vehicleapiservice.common.exceptions.VehicleNotFoundException;
import net.space.developer.vehicleapiservice.domain.DieselVehicle;
import net.space.developer.vehicleapiservice.domain.ElectricalVehicle;
import net.space.developer.vehicleapiservice.domain.GasolineVehicle;
import net.space.developer.vehicleapiservice.domain.Vehicle;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.mapper.VehicleMapper;
import net.space.developer.vehicleapiservice.model.DieselModel;
import net.space.developer.vehicleapiservice.model.ElectricalModel;
import net.space.developer.vehicleapiservice.model.GasolineModel;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import net.space.developer.vehicleapiservice.repository.DieselRepository;
import net.space.developer.vehicleapiservice.repository.ElectricalRepository;
import net.space.developer.vehicleapiservice.repository.GasolineRepository;
import net.space.developer.vehicleapiservice.repository.InventoryRepository;
import net.space.developer.vehicleapiservice.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    /**
     * Vehicle mapper injection
     */
    private final VehicleMapper vehicleMapper;

    /**
     * Diesel repository injection
     */
    private final DieselRepository dieselRepository;

    /**
     * Gasoline repository injection
     */
    private final GasolineRepository gasolineRepository;

    /**
     * Electrical repository injection
     */
    private final ElectricalRepository electricalRepository;

    /**
     * Vehicle inventory repository injection
     */
    private final InventoryRepository inventoryRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleModel> getAllVehicles() {
        return inventoryRepository.findAll()
                .stream()
                .map(vehicleMapper::toVehicleModel)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<VehicleModel> getAllVehicles(Pageable pageable) {

        Page<Vehicle> vehicleModels = inventoryRepository.findAll(pageable);

        var response = vehicleModels.getContent().stream().map(vehicleMapper::toVehicleModel).toList();

        return new PageImpl<>(response, pageable, vehicleModels.getTotalElements());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleModel> getVehiclesByType(VehicleType type) {
        switch (type) {
            case DIESEL -> {
                return dieselRepository.findAll().stream().map(vehicleMapper::toVehicleModel).toList();
            }
            case ELECTRICAL -> {
                return electricalRepository.findAll().stream().map(vehicleMapper::toVehicleModel).toList();
            }
            case GASOLINE -> {
                return gasolineRepository.findAll().stream().map(vehicleMapper::toVehicleModel).toList();
            }
            default -> {
                return inventoryRepository.findAll().stream().map(vehicleMapper::toVehicleModel).toList();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<VehicleModel> getVehiclesByType(VehicleType type, Pageable pageable) {
        Page<Vehicle> vehiclePage = inventoryRepository.findAll(pageable);
        var response = vehiclePage.getContent().stream().map(vehicleMapper::toVehicleModel).toList();
        return new PageImpl<>(response, pageable, vehiclePage.getTotalElements());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleModel getVehicleById(long id) {
        Optional<Vehicle> optionalVehicle = inventoryRepository.findById(id);

        if(optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();

            switch (vehicle) {
                case DieselVehicle dieselVehicle -> {
                    return vehicleMapper.toDieselModel(dieselVehicle);
                }
                case GasolineVehicle gasolineVehicle -> {
                    return vehicleMapper.toGasolineModel(gasolineVehicle);
                }
                case ElectricalVehicle electricalVehicle -> {
                    return vehicleMapper.toElectricalModel(electricalVehicle);
                }
                default -> throw new VehicleInvalidException();
            }

        }

        throw new VehicleNotFoundException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleModel createVehicle(VehicleModel vehicleModel) {

        Vehicle vehicle;

        switch (vehicleModel) {
            case DieselModel dieselModel -> {
                vehicle = new DieselVehicle();
                BeanUtils.copyProperties(dieselModel, vehicle);
            }
            case ElectricalModel electricalModel -> {
                vehicle = new ElectricalVehicle();
                BeanUtils.copyProperties(electricalModel, vehicle);
            }
            case GasolineModel gasolineModel -> {
                vehicle = new GasolineVehicle();
                BeanUtils.copyProperties(gasolineModel, vehicle);
            }
            default -> throw new VehicleInvalidException(vehicleModel);
        }

        if(isAlreadyRegistered(vehicle)) {
            throw new VehicleAlreadyRegisteredException();
        }

        var savedVehicle = inventoryRepository.save(vehicle);

        return vehicleMapper.toVehicleModel(savedVehicle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleModel updateVehicle(VehicleModel vehicleModel, long id) {

        Optional<Vehicle> optionalVehicle = inventoryRepository.findById(id);

        if(optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();

            BeanUtils.copyProperties(vehicleModel, vehicle);

            vehicle.setId(id);

            var savedVehicle = inventoryRepository.save(vehicle);

            return vehicleMapper.toVehicleModel(savedVehicle);
        }

        throw new VehicleNotFoundException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteVehicle(long id) {
        Optional<Vehicle> optionalVehicle = inventoryRepository.findById(id);

        if(optionalVehicle.isPresent()) {
            inventoryRepository.delete(optionalVehicle.get());
            return true;
        }

        return false;
    }

    private boolean isAlreadyRegistered(Vehicle vehicle) {
        return (
                inventoryRepository.existsByVehicleIdentificationNumber(vehicle.getVehicleIdentificationNumber())
                || inventoryRepository.existsByVehicleRegistration(vehicle.getVehicleRegistration())
        );
    }
}
