package com.github.ravisankarchinnam.speed.service.car;

import java.util.List;

import com.github.ravisankarchinnam.speed.model.CarDO;
import com.github.ravisankarchinnam.speed.exception.ConstraintsViolationException;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;

/**
 * CarService Interface should be implemented.
 * <p/>
 */
public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;


    CarDO create(CarDO carDO) throws ConstraintsViolationException;


    void delete(Long carId) throws EntityNotFoundException;


    List<CarDO> findByConvertible(boolean convertible);


    List<CarDO> findByRating(double rating);


    List<CarDO> findByManufacturer(String manufacturer);


    List<CarDO> findByEngineType(String engineType);


    void updateRating(CarDO carDO, double rating) throws EntityNotFoundException;

}
