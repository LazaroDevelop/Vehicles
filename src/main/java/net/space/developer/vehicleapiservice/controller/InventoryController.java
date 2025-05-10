package net.space.developer.vehicleapiservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.space.developer.vehicleapiservice.common.assembler.ElectricalRegisterInfoModelAssembler;
import net.space.developer.vehicleapiservice.common.assembler.RegistrationModelAssembler;
import net.space.developer.vehicleapiservice.common.assembler.VehicleModelAssembler;
import net.space.developer.vehicleapiservice.enums.VehicleType;
import net.space.developer.vehicleapiservice.enums.gasoline.GasolineType;
import net.space.developer.vehicleapiservice.model.RegistrationModel;
import net.space.developer.vehicleapiservice.model.VehicleModel;
import net.space.developer.vehicleapiservice.model.electrical.ElectricalRegisterInfo;
import net.space.developer.vehicleapiservice.service.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static net.space.developer.vehicleapiservice.common.constants.ApplicationConstants.API_BASE_URL;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Inventory controller class to manage all the endpoints operations
 *
 * @author Lazaro Noel Guerra Medina
 * @since 2025-05-09
 */

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(API_BASE_URL)
@RequiredArgsConstructor
public class InventoryController {

    /**
     * Inventory service dependency injection
     */
    private final InventoryService inventoryService;

    /**
     * Vehicle model assembler dependency injection
     */
    private final VehicleModelAssembler vehicleModelAssembler;

    /**
     * Registration model assembler dependency injection
     */
    private final RegistrationModelAssembler registrationModelAssembler;

    /**
     * Registration model assembler dependency injection
     */
    private final ElectricalRegisterInfoModelAssembler electricalRegisterInfoModelAssembler;

    /**
     * Endpoint to get all the vehicles
     *
     * @return a {@link ResponseEntity} with the response information
     */
    @GetMapping("/all")
    public ResponseEntity<CollectionModel<EntityModel<VehicleModel>>> getAllVehicles(){
        var response = inventoryService.getAllVehicles();

        return getCollectionModel(response);
    }

    /**
     * Endpoint to get the vehicles registration info
     *
     * @return the {@link RegistrationModel}
     */
    @GetMapping("/vehicles/registration")
    public ResponseEntity<EntityModel<RegistrationModel>> getVehiclesRegistration(){
        RegistrationModel model = inventoryService.getVehiclesRegistration();

        if(Objects.isNull(model)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(registrationModelAssembler.toModel(model));
    }

    /**
     * Endpoint to get all the vehicles using pagination
     *
     * @param page the page number
     * @param size the page size
     * @param sort the sort params
     * @return a {@link ResponseEntity} with the paginated response information
     */
    @GetMapping("/all/paginated")
    public ResponseEntity<CollectionModel<EntityModel<VehicleModel>>> getAllPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") List<String> sort
    ){
        String field = sort.getFirst();
        String dir = sort.getLast();

        Sort.Direction direction = dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest request = PageRequest.of(page, size, Sort.by(direction, field));

        var response = inventoryService.getAllVehicles(request);

        return getCollectionModel(response);
    }

    /**
     * Endpoint to get all the vehicles by type
     *
     * @param type the type of vehicle {@link VehicleType}
     * @return a {@link ResponseEntity} with the response information
     */
    @GetMapping("/all/by-vehicles")
    public ResponseEntity<CollectionModel<EntityModel<VehicleModel>>> getAllByVehicleType(@RequestParam("type") String type){

        VehicleType vehicleType = VehicleType.valueOf(type);

        var response = inventoryService.getVehiclesByType(vehicleType);

        return getCollectionModel(response);
    }

    /**
     * Endpoint to get all the vehicles by type using pagination
     *
     * @param type the type of vehicle {@link VehicleType}
     * @return a {@link ResponseEntity} with the paginated response information
     */
    @GetMapping("/all/by-vehicles/paginated")
    public ResponseEntity<CollectionModel<EntityModel<VehicleModel>>> getAllByVehicleTypePaginated(
            @RequestParam("type") String type,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") List<String> sort
    ){

        String field = sort.getFirst();
        String dir = sort.getLast();

        Sort.Direction direction = dir.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest request = PageRequest.of(page, size, Sort.by(direction, field));

        VehicleType vehicleType = VehicleType.valueOf(type);

        var response = inventoryService.getVehiclesByType(vehicleType, request);

        return getCollectionModel(response);
    }

    /**
     * Endpoint to get a specific vehicle by identifier
     *
     * @param id the given id
     * @return a {@link ResponseEntity} of the found instance of {@link VehicleModel}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<VehicleModel>> getVehicleById(@PathVariable("id") Long id){
        var response = inventoryService.getVehicleById(id);

        if(Objects.isNull(response)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(vehicleModelAssembler.toModel(response));
    }

    /**
     * Endpoint to create and store a new vehicle information
     *
     * @param vehicle the vehicle information {@link VehicleModel}
     * @return a {@link ResponseEntity} with saved or stored {@link VehicleModel} instance
     */
    @PostMapping("/create")
    public ResponseEntity<EntityModel<VehicleModel>> createVehicle(@RequestBody VehicleModel vehicle){
        var response = inventoryService.createVehicle(vehicle);

        if(Objects.isNull(response)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleModelAssembler.toModel(response));
    }

    /**
     * Endpoint to update an existing vehicle by identifier
     *
     * @param id the vehicle identifier
     * @param vehicle new information to update
     * @return a {@link ResponseEntity} with the updated instance of {@link VehicleModel}
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<EntityModel<VehicleModel>> updateVehicle(@PathVariable("id") Long id, @RequestBody VehicleModel vehicle){
        var response = inventoryService.updateVehicle(vehicle, id);

        if(Objects.isNull(response)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(vehicleModelAssembler.toModel(response));
    }

    /**
     * Endpoint to transform or convert an electrical vehicle to a gasoline vehicle
     *
     * @param id the identifier of the electrical vehicle
     * @param gasolineTypes the set of gasoline's that use the transformed car
     * @return a {@link ResponseEntity} the converted instance of the vehicle {@link VehicleModel}
     */
    @PostMapping("/convert/{id}")
    public ResponseEntity<EntityModel<ElectricalRegisterInfo>> convertVehicle(@PathVariable("id") Long id, @RequestBody Set<GasolineType> gasolineTypes){

        var response = inventoryService.transformIntoGasoline(id, gasolineTypes);

        if(Objects.isNull(response)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(electricalRegisterInfoModelAssembler.toModel(response));
    }

    /**
     * Endpoint to delete a specific vehicle by identifier
     *
     * @param id the identifier of the vehicle
     * @return a {@link ResponseEntity} with status content
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long id){
        inventoryService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get the collection model of the vehicles
     *
     * @param models the list of vehicle models
     * @return a {@link ResponseEntity} with the collection model of the vehicles
     */
    private ResponseEntity<CollectionModel<EntityModel<VehicleModel>>> getCollectionModel(List<VehicleModel> models){
        if(Objects.isNull(models) || models.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        var result = models.stream().map(vehicleModelAssembler::toModel).toList();

        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                result,
                linkTo(methodOn(InventoryController.class).getAllVehicles()).withSelfRel()
        ));
    }

    /**
     * Get the collection model of the vehicles with paged information
     *
     * @param models the list of paged vehicle models
     * @return a {@link ResponseEntity} with the collection model of the vehicles
     */
    private ResponseEntity<CollectionModel<EntityModel<VehicleModel>>> getCollectionModel(Page<VehicleModel> models){
        if(Objects.isNull(models) || models.getContent().isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(
                models.map(vehicleModelAssembler::toModel),
                linkTo(methodOn(InventoryController.class).getAllVehicles()).withSelfRel()
        ));
    }
}
