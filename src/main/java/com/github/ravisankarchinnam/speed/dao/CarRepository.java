package com.github.ravisankarchinnam.speed.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.ravisankarchinnam.speed.model.CarDO;

/**
 * Database Access Object for Car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{

    List<CarDO> findAllByEngineType(String engineType);


    List<CarDO> findAllByConvertible(boolean isConvertible);


    List<CarDO> findAllByRating(double rating);


    List<CarDO> findByManufacturerManufacturerLikeIgnoreCase(String manufacturer);
}
