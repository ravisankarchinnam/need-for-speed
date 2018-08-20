package com.github.ravisankarchinnam.speed.dao;

import org.springframework.data.repository.CrudRepository;

import com.github.ravisankarchinnam.speed.model.ManufacturerDO;

/**
 * Database Access Object for Manufacturer table.
 * <p/>
 */
public interface ManufacturerRepository extends CrudRepository<ManufacturerDO, Long>
{

    ManufacturerDO findByManufacturer(String manufacturer);
}
