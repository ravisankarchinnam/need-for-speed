package com.github.ravisankarchinnam.speed.service.manufacturer;

import static org.junit.Assert.assertNotNull;

import com.github.ravisankarchinnam.speed.MyAppBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.ravisankarchinnam.speed.model.ManufacturerDO;
import com.github.ravisankarchinnam.speed.exception.ConstraintsViolationException;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;

public class ManufacturerServiceTest extends MyAppBaseTest
{

    @Autowired
    private ManufacturerService manufacturerService;


    @Test
    public void testFind() throws EntityNotFoundException
    {
        long carId = 1;
        ManufacturerDO find = manufacturerService.find(carId);
        assertNotNull(find);
    }


    @Test(expected = EntityNotFoundException.class)
    public void testFindException() throws EntityNotFoundException
    {
        long carId = 15;
        manufacturerService.find(carId);
    }


    @Test
    public void testFindCarChecked() throws EntityNotFoundException
    {
        long carId = 1;
        ManufacturerDO find = manufacturerService.find(carId);
        assertNotNull(find);
    }


    @Test(expected = EntityNotFoundException.class)
    public void testFindCarCheckedException() throws EntityNotFoundException
    {
        long carId = 15;
        manufacturerService.find(carId);
    }


    @Test
    public void testFindManufacturerName() throws EntityNotFoundException
    {
        String manufacturerContain = "Mercedes";
        ManufacturerDO findByManufacturer = manufacturerService.findManufacturerName(manufacturerContain);
        assertNotNull(findByManufacturer);
    }


    @Test(expected = EntityNotFoundException.class)
    public void testFindManufacturerNameException() throws EntityNotFoundException
    {
        String manufacturerContain = "Ford";
        manufacturerService.findManufacturerName(manufacturerContain);
    }


    @Test
    public void testFindManufacturerChecked() throws EntityNotFoundException
    {
        String manufacturerContain = "Mercedes";
        ManufacturerDO findByManufacturer = manufacturerService.findManufacturerName(manufacturerContain);
        assertNotNull(findByManufacturer);
    }


    @Test(expected = EntityNotFoundException.class)
    public void testFindManufacturerCheckedException() throws EntityNotFoundException
    {
        String manufacturerContain = "Hero";
        manufacturerService.findManufacturerName(manufacturerContain);
    }


    @Test(expected = ConstraintsViolationException.class)
    public void testCreateException() throws ConstraintsViolationException, EntityNotFoundException
    {
        ManufacturerDO manufacturerDO = manufacturerService.find(1L);
        manufacturerDO.setManufacturer("Ferrari");
        manufacturerService.create(manufacturerDO);
    }
}
