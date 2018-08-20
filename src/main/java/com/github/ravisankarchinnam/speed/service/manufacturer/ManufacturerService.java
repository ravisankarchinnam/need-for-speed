package com.github.ravisankarchinnam.speed.service.manufacturer;

import com.github.ravisankarchinnam.speed.model.ManufacturerDO;
import com.github.ravisankarchinnam.speed.exception.ConstraintsViolationException;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;

/**
 * ManufacturerService Interface should be implemented.
 * <p/>
 */
public interface ManufacturerService
{

    ManufacturerDO find(Long manufacturerId) throws EntityNotFoundException;


    ManufacturerDO findManufacturerName(String manufacturerName) throws EntityNotFoundException;


    ManufacturerDO create(ManufacturerDO manufacturerDO) throws ConstraintsViolationException;
}
