package com.github.ravisankarchinnam.speed.web;

import java.util.List;

import javax.validation.Valid;

import com.github.ravisankarchinnam.speed.service.car.CarService;
import com.github.ravisankarchinnam.speed.service.manufacturer.ManufacturerService;
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

import com.github.ravisankarchinnam.speed.web.mapper.CarMapper;
import com.github.ravisankarchinnam.speed.dto.CarDTO;
import com.github.ravisankarchinnam.speed.model.CarDO;
import com.github.ravisankarchinnam.speed.model.ManufacturerDO;
import com.github.ravisankarchinnam.speed.enums.ConvertibleValue;
import com.github.ravisankarchinnam.speed.enums.EngineType;
import com.github.ravisankarchinnam.speed.exception.ConstraintsViolationException;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;

/**
 * All Requests related to Car will be served by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{

    private final CarService carService;
    private final ManufacturerService manufacturerService;


    @Autowired
    public CarController(final CarService carService, final ManufacturerService manufacturerService)
    {
        this.carService = carService;
        this.manufacturerService = manufacturerService;
    }


    /**
     * get car by car id.
     * 
     * @param carId
     * @return a CarDTO.
     * @throws EntityNotFoundException
     */
    @GetMapping("/{carId}")
    public CarDTO getCarById(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    /**
     * get all cars by Engine type.
     * 
     * @param engineType
     * @return list of CardDTO
     * @throws ConstraintsViolationException
     * @throws EntityNotFoundException
     */
    @GetMapping
    public List<CarDTO> findByEngineType(@Valid @RequestParam EngineType engineType)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return CarMapper.makeDriverDTOList(carService.findByEngineType(engineType.type()));
    }


    /**
     * get all cars by convertible.
     * 
     * @param convertible
     * @return List of cardDTO
     * @throws EntityNotFoundException
     */
    @GetMapping("/convertible/{convertible}")
    public List<CarDTO> findByConvertible(@Valid @PathVariable ConvertibleValue convertible) throws EntityNotFoundException
    {
        return CarMapper.makeDriverDTOList(carService.findByConvertible(convertible.value()));
    }


    /**
     * get all cars by rating.
     * 
     * @param convertible
     * @return List of cardDTO
     * @throws EntityNotFoundException
     */
    @GetMapping("/rating/{rating}")
    public List<CarDTO> findByRating(@Valid @PathVariable double rating) throws EntityNotFoundException
    {
        return CarMapper.makeDriverDTOList(carService.findByRating(rating));
    }


    /**
     * get all cars by Manufacturer.
     * 
     * @param manufacturer
     * @return list of carDTO
     * @throws EntityNotFoundException
     */
    @GetMapping("/manufacturer/{manufacturer}")
    public List<CarDTO> findByManufacturer(@Valid @PathVariable String manufacturer) throws EntityNotFoundException
    {
        return CarMapper.makeDriverDTOList(carService.findByManufacturer(manufacturer));
    }


    /**
     * update rating for a car.
     * 
     * @param carId
     * @param ratingType
     * @throws ConstraintsViolationException
     * @throws EntityNotFoundException
     */
    @PutMapping("/rating/{carId}")
    public void updateRating(
        @Valid @PathVariable long carId, @RequestParam double rating)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        CarDO cardDO = carService.find(carId);
        carService.updateRating(cardDO, rating);
    }


    /**
     * create new CarDo If the Manufacturer wasn't placed, will be set with null If
     * Manufacturer Name is placed , will check if exist in DB or not if it's not
     * exist in DB will create new one, and if exist will use it.
     * 
     * @param carDTO
     * @return CarDTO
     * @throws ConstraintsViolationException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO makeCarDO = CarMapper.makeCarDO(carDTO);
        ManufacturerDO manufacturer = makeCarDO.getManufacturer();

        if (null != manufacturer)
        {
            try
            {
                manufacturer = manufacturerService.findManufacturerName(manufacturer.getManufacturer());
            }
            catch (EntityNotFoundException e1)
            {
                manufacturer = manufacturerService.create(new ManufacturerDO(manufacturer.getManufacturer()));
            }
        }
        makeCarDO.setManufacturer(manufacturer);
        return CarMapper.makeCarDTO(carService.create(makeCarDO));
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }

}
