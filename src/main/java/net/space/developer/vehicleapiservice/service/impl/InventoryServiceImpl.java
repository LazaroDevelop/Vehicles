package net.space.developer.vehicleapiservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleAlreadyRegisteredException;
import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleConversionFailedException;
import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleInvalidException;
import net.space.developer.vehicleapiservice.common.exceptions.custom_exceptions.VehicleNotFoundException;
import net.space.developer.vehicleapiservice.domain.DieselVehicle;
import net.space.developer.vehicleapiservice.domain.ElectricalVehicle;
import net.space.developer.vehicleapiservice.domain.GasolineVehicle;
import net.space.developer.vehicleapiservice.domain.Vehicle;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.mapper.VehicleMapper;
import net.space.developer.vehicleapiservice.model.RegistrationModel;
import net.space.developer.vehicleapiservice.model.diesel.DieselModel;
import net.space.developer.vehicleapiservice.model.diesel.DieselRegisterInfo;
import net.space.developer.vehicleapiservice.model.electrical.ConvertedInfo;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalModel;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalRegisterInfo;
import net.space.developer.vehicleapiservice.model.gasoline.GasolineModel;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import net.space.developer.vehicleapiservice.model.gasoline.GasolineRegisterInfo;
import net.space.developer.vehicleapiservice.repository.DieselRepository;
import net.space.developer.vehicleapiservice.repository.ElectricalRepository;
import net.space.developer.vehicleapiservice.repository.GasolineRepository;
import net.space.developer.vehicleapiservice.repository.InventoryRepository;
import net.space.developer.vehicleapiservice.service.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Inventory service implementation class
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

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
    public RegistrationModel getVehiclesRegistration() {
        List<ElectricalRegisterInfo> electricalRegisterInfos = new LinkedList<>();
        List<GasolineRegisterInfo> gasolineRegisterInfos = new LinkedList<>();
        List<DieselRegisterInfo> dieselRegisterInfos = new LinkedList<>();

        electricalRepository.findAll()
                .stream()
                .map(this::mapToElectrical)
                .collect(Collectors.toCollection(() -> electricalRegisterInfos));

        gasolineRepository.findAll()
                .stream()
                .map(this::mapToGasoline)
                .collect(Collectors.toCollection(() -> gasolineRegisterInfos));

        dieselRepository.findAll()
                .stream()
                .map(this::mapToDiesel)
                .collect(Collectors.toCollection(() -> dieselRegisterInfos));

        return RegistrationModel.builder()
                .electricalRegisterInfos(electricalRegisterInfos)
                .dieselRegisterInfos(dieselRegisterInfos)
                .gasolineRegisterInfos(gasolineRegisterInfos)
                .build();
    }

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

        return inventoryRepository.findAll()
                .stream()
                .filter(vehicle -> type.equals(vehicle.getVehicleType()))
                .map(vehicleMapper::toVehicleModel)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<VehicleModel> getVehiclesByType(VehicleType type, Pageable pageable) {

        Page<Vehicle> vehiclePage = inventoryRepository.findAll(pageable);

        var response = vehiclePage.getContent()
                .stream()
                .filter(vehicle -> type.equals(vehicle.getVehicleType()))
                .map(vehicleMapper::toVehicleModel)
                .toList();

        return new PageImpl<>(response, pageable, vehiclePage.getTotalElements());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleModel getVehicleById(long id) {

        Vehicle vehicle = inventoryRepository
                .findById(id)
                .orElseThrow(VehicleNotFoundException::new);


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

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleModel createVehicle(VehicleModel vehicleModel) {

        Vehicle vehicle = switch (vehicleModel.getVehicleType()) {
            case DIESEL -> vehicleMapper.toDieselVehicle((DieselModel) vehicleModel); 
            case ELECTRICAL -> vehicleMapper.toElectricalVehicle((ElectricalModel) vehicleModel);
            case GASOLINE -> vehicleMapper.toGasolineVehicle((GasolineModel) vehicleModel);
        };

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

        Vehicle vehicle = inventoryRepository
                .findById(id)
                .orElseThrow(VehicleNotFoundException::new);

        switch (vehicle) {
            case DieselVehicle dieselVehicle when vehicleModel instanceof DieselModel dieselModel -> {
                updateDieselVehicle(dieselVehicle, dieselModel);
                return vehicleMapper.toDieselModel(inventoryRepository.save(dieselVehicle));
            }
            case ElectricalVehicle electricalVehicle when vehicleModel instanceof ElectricalModel electricalModel -> {
                updateElectricalVehicle(electricalVehicle, electricalModel);
                return vehicleMapper.toElectricalModel(inventoryRepository.save(electricalVehicle));
            }
            case GasolineVehicle gasolineVehicle when vehicleModel instanceof GasolineModel gasolineModel -> {
                updateGasolineVehicle(gasolineVehicle, gasolineModel);
                return vehicleMapper.toGasolineModel(inventoryRepository.save(gasolineVehicle));
            }
            default -> throw new VehicleInvalidException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ElectricalRegisterInfo transformIntoGasoline(long id, Set<GasolineType> gasolineTypes) {

        ElectricalVehicle vehicle = inventoryRepository.findById(id)
                .filter(ElectricalVehicle.class::isInstance)
                .map(v -> (ElectricalVehicle) v)
                .filter(v -> !v.isReconverted())
                .orElseThrow(VehicleConversionFailedException::new);

        vehicle.setReconverted(true);
        vehicle.setFuelTypePostConversion(gasolineTypes);

        var result = inventoryRepository.save(vehicle);

        return mapToElectrical(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteVehicle(long id) {

        Vehicle vehicle = inventoryRepository
                .findById(id)
                .orElseThrow(VehicleNotFoundException::new);

        inventoryRepository.delete(vehicle);
    }

    private boolean isAlreadyRegistered(Vehicle vehicle) {
        return (
                inventoryRepository.existsByVehicleIdentificationNumber(vehicle.getVehicleIdentificationNumber())
                || inventoryRepository.existsByVehicleRegistration(vehicle.getVehicleRegistration())
        );
    }

    private ElectricalRegisterInfo mapToElectrical(ElectricalVehicle vehicle){

        ElectricalRegisterInfo eri = new ElectricalRegisterInfo();

        eri.setVehicleIdentificationNumber(vehicle.getVehicleIdentificationNumber());
        eri.setBatteryType(vehicle.getBatteryType());
        eri.setBatteryCurrent(vehicle.getCurrent());
        eri.setBatteryVoltage(vehicle.getVoltage());

        if(vehicle.isReconverted()){
            eri.setReconverted(true);
            eri.setConvertedInfo(new ConvertedInfo(vehicle.getVehicleRegistration(), vehicle.getFuelTypePostConversion()));
        }else{
            eri.setReconverted(false);
        }

        return eri;
    }

    private GasolineRegisterInfo mapToGasoline(GasolineVehicle vehicle){
        return GasolineRegisterInfo.builder()
                .vehicleRegistration(vehicle.getVehicleRegistration())
                .types(vehicle.getGasolineType())
                .build();
    }

    private DieselRegisterInfo mapToDiesel(DieselVehicle vehicle){
        return DieselRegisterInfo.builder()
                .vehicleRegistration(vehicle.getVehicleRegistration())
                .pumpType(vehicle.getPumpType())
                .build();
    }

    private void updateDieselVehicle(DieselVehicle dieselVehicle, DieselModel dieselModel) {
        dieselVehicle.setVehicleRegistration(dieselModel.getVehicleRegistration());
        dieselVehicle.setVehicleIdentificationNumber(dieselModel.getVehicleIdentificationNumber());
        dieselVehicle.setPumpType(dieselModel.getPumpType());
    }

    private void updateElectricalVehicle(ElectricalVehicle electricalVehicle, ElectricalModel electricalModel) {
        electricalVehicle.setVehicleRegistration(electricalModel.getVehicleRegistration());
        electricalVehicle.setVehicleIdentificationNumber(electricalModel.getVehicleIdentificationNumber());
        electricalVehicle.setBatteryType(electricalModel.getBatteryType());
        electricalVehicle.setVoltage(electricalModel.getVoltage());
        electricalVehicle.setCurrent(electricalModel.getCurrent());
    }

    private void updateGasolineVehicle(GasolineVehicle gasolineVehicle, GasolineModel gasolineModel) {
        gasolineVehicle.setVehicleRegistration(gasolineModel.getVehicleRegistration());
        gasolineVehicle.setVehicleIdentificationNumber(gasolineModel.getVehicleIdentificationNumber());
        gasolineVehicle.setGasolineType(gasolineModel.getGasolineType());
    }
}
