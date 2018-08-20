package com.github.ravisankarchinnam.speed.service.manufacturer;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.ravisankarchinnam.speed.dao.ManufacturerRepository;
import com.github.ravisankarchinnam.speed.model.ManufacturerDO;
import com.github.ravisankarchinnam.speed.exception.ConstraintsViolationException;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for some Manufacturer specific things.
 */
@Service("ManufacturerService")
public class DefaultManufacturerService implements ManufacturerService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultManufacturerService.class);

    private final ManufacturerRepository manufacturerRepository;


    @Autowired
    public DefaultManufacturerService(final ManufacturerRepository manufacturerRepository)
    {
        this.manufacturerRepository = manufacturerRepository;
    }


    /**
     * Creates a new Manufacturer.
     *
     * @param ManufacturerDO
     * @return
     * @throws ConstraintsViolationException if a Manufacturer already exists with the given licensePlate, ... .
     */
    @Override
    public ManufacturerDO create(ManufacturerDO manufacturerDO) throws ConstraintsViolationException
    {
        ManufacturerDO manufacturer = null;
        try
        {
            manufacturer = manufacturerRepository.save(manufacturerDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to manufacturer creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return manufacturer;
    }


    /**
     * Select manufacturer by id
     * 
     * @param manufacturerId
     * @return found manufacturerDo
     * @throws EntityNotFoundException if no manufacturerId with the given id was found.
     */
    @Override
    public ManufacturerDO find(Long manufacturerId) throws EntityNotFoundException
    {
        return findManufacturerChecked(manufacturerId);
    }


    /**
     * Select manufacturer by manufacturerName
     * 
     * @param manufacturerName
     * @return found manufacturerDo
     * @throws EntityNotFoundException if no manufacturerId with the given manufacturerName was found.
     */
    @Override
    public ManufacturerDO findManufacturerName(String manufacturerName) throws EntityNotFoundException
    {
        return findManufacturerChecked(manufacturerName);
    }


    private ManufacturerDO findManufacturerChecked(Long manufacturerId) throws EntityNotFoundException
    {
        ManufacturerDO manufacturerDO = manufacturerRepository.findOne(manufacturerId);
        if (null == manufacturerDO)
        {
            if (LOG.isWarnEnabled())
            {
                LOG.warn("Could not find manufacturer with id: " + manufacturerId);
            }
            throw new EntityNotFoundException("Could not find manufacturer with id: " + manufacturerId);
        }
        return manufacturerDO;
    }


    private ManufacturerDO findManufacturerChecked(String manufacturerName) throws EntityNotFoundException
    {
        ManufacturerDO manufacturerDO = manufacturerRepository.findByManufacturer(manufacturerName);
        if (null == manufacturerDO)
        {
            if (LOG.isWarnEnabled())
            {
                LOG.warn("Could not find manufacturer with manufacturer name: " + manufacturerName);
            }
            throw new EntityNotFoundException("Could not find manufacturer with manufacturer name: " + manufacturerName);
        }
        return manufacturerDO;
    }

}
