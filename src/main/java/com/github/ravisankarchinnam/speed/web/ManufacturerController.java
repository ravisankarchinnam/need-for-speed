package com.github.ravisankarchinnam.speed.web;

import javax.validation.Valid;

import com.github.ravisankarchinnam.speed.exception.ConstraintsViolationException;
import com.github.ravisankarchinnam.speed.service.manufacturer.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.ravisankarchinnam.speed.web.mapper.ManufacturerMapper;
import com.github.ravisankarchinnam.speed.dto.ManufacturerDTO;
import com.github.ravisankarchinnam.speed.model.ManufacturerDO;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;

/**
 * All operations with a manufacturers will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/manufacturers")
public class ManufacturerController
{

    private final ManufacturerService manufacturerService;


    @Autowired
    public ManufacturerController(final ManufacturerService manufacturerService)
    {
        this.manufacturerService = manufacturerService;
    }


    /**
     * get Manufacturer By id.
     * 
     * @param manufacturerId
     * @return ManufacturerDTO
     * @throws EntityNotFoundException
     */
    @GetMapping("/{manufacturerId}")
    public ManufacturerDTO getManufacturerById(@Valid @PathVariable long manufacturerId)
        throws EntityNotFoundException
    {
        return ManufacturerMapper.makeManufacturerDTO(manufacturerService.find(manufacturerId));
    }


    /**
     * get Manufacturer by name.
     * 
     * @param manufacturerName
     * @return ManufacturerDTO
     * @throws EntityNotFoundException
     */
    @GetMapping
    public ManufacturerDTO getManufacturerByName(@Valid @RequestParam String manufacturerName)
        throws EntityNotFoundException
    {
        return ManufacturerMapper.makeManufacturerDTO(manufacturerService.findManufacturerName(manufacturerName));
    }


    /**
     * creates new Manufacturer.
     * 
     * @param manufacturerDTO
     * @return manufacturerDTO
     * @throws ConstraintsViolationException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturerDTO createManufacturer(@Valid @RequestBody ManufacturerDTO manufacturerDTO)
        throws ConstraintsViolationException
    {
        ManufacturerDO makeManufacturerDO = ManufacturerMapper.makeManufacturerDO(manufacturerDTO);
        return ManufacturerMapper.makeManufacturerDTO(manufacturerService.create(makeManufacturerDO));
    }

}
