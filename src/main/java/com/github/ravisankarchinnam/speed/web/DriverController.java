package com.github.ravisankarchinnam.speed.web;

import java.util.List;

import javax.validation.Valid;

import com.github.ravisankarchinnam.speed.web.mapper.DriverMapper;
import com.github.ravisankarchinnam.speed.enums.EngineType;
import com.github.ravisankarchinnam.speed.enums.OnlineStatus;
import com.github.ravisankarchinnam.speed.exception.CarAlreadyInUseException;
import com.github.ravisankarchinnam.speed.exception.ConstraintsViolationException;
import com.github.ravisankarchinnam.speed.exception.DriverOfflineException;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;
import com.github.ravisankarchinnam.speed.service.car.CarService;
import com.github.ravisankarchinnam.speed.service.driver.DriverService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.ravisankarchinnam.speed.dto.DriverDTO;
import com.github.ravisankarchinnam.speed.model.CarDO;
import com.github.ravisankarchinnam.speed.model.DriverDO;
import com.github.ravisankarchinnam.speed.enums.ConvertibleValue;
import com.github.ravisankarchinnam.speed.dto.DriverFilterDTO;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    private final DriverService driverService;

    private final CarService carService;


    @Autowired
    public DriverController(final DriverService driverService, final CarService carService)
    {
        this.driverService = driverService;
        this.carService = carService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }


    /**
     * select a Car by drivers to drive through.
     * 
     * @param driverId
     * @param carId
     * @throws EntityNotFoundException
     * @throws DriverOfflineException
     * @throws CarAlreadyInUseException
     */
    @PutMapping("/selectcar")
    public void selectCar(@Valid @RequestParam long driverId, @RequestParam long carId)
        throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException
    {
        CarDO carDO = carService.find(carId);
        driverService.selectCar(driverId, carDO);
    }


    /**
     * deselect a Car by drivers.
     * 
     * @param driverId
     * @throws EntityNotFoundException 
     */
    @PutMapping("/deselectcar")
    public void deSelectCar(@Valid @RequestParam long driverId) throws EntityNotFoundException
    {
        driverService.deSelectCar(driverId);
    }


    /**
     * post method returns list of drivers using filter pattern.
     * 
     * @param driverFilterDTO
     * @return List<DriverDTO>
     */
    @PostMapping("/filter")
    public List<DriverDTO> findDrivers(@RequestBody DriverFilterDTO driverFilterDTO)
    {
        return DriverMapper.makeDriverDTOList(driverService.find(driverFilterDTO.toDriverFilterParams()));
    }


    /**
     * get method returns list of drivers using filter pattern.
     * 
     * @param engineType
     * @param convertible
     * @param licensePlate
     * @return List<DriverDTO>
     */
    @GetMapping("/filter")
    public List<DriverDTO> filter(
        @RequestParam(required = false) EngineType engineType,
        @RequestParam(required = false) ConvertibleValue convertible,
        @RequestParam(required = false) String licensePlate,
        @RequestParam(required = false) String rating)
    {
        String engine = (null == engineType) ? null : engineType.type();
        Boolean convertibleState = (null == convertible) ? null : convertible.value();
        double qRating = StringUtils.isNotEmpty(rating) ? Double.valueOf(rating) : 0;
        CarDO carDO = new CarDO(licensePlate, 0, convertibleState, qRating, engine, null);
        return DriverMapper.makeDriverDTOList(driverService.filter(carDO));
    }

}
