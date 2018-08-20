package com.github.ravisankarchinnam.speed.service.driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import com.github.ravisankarchinnam.speed.MyAppBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.ravisankarchinnam.speed.model.CarDO;
import com.github.ravisankarchinnam.speed.model.DriverDO;
import com.github.ravisankarchinnam.speed.exception.CarAlreadyInUseException;
import com.github.ravisankarchinnam.speed.exception.DriverOfflineException;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;
import com.github.ravisankarchinnam.speed.service.car.CarService;

public class DriverServiceTest extends MyAppBaseTest
{

    @Autowired
    private DriverService driverService;
    @Autowired
    private CarService carService;


    @Test
    public void testFindByUsername()
    {
        String driverName = "driver06";
        DriverDO findByUsername = driverService.findByUsername(driverName);
        assertNotNull(findByUsername);
        driverName = "test";
        findByUsername = driverService.findByUsername(driverName);
        assertNull(findByUsername);
    }




    @Test(expected = EntityNotFoundException.class)
    public void testSelectCarENFE() throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException
    {
        CarDO carDO = carService.find(1L);
        long driverId = 0;
        driverService.selectCar(driverId, carDO);
    }


    @Test
    public void testDeSelectCar() throws EntityNotFoundException
    {
        long driverId = 6;
        driverService.deSelectCar(driverId);
        DriverDO find = driverService.find(driverId);
        assertNull(find.getCar());
    }


    @Test
    public void testFilter() throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException
    {
        CarDO carDO = carService.find(2L);
        long driverId = 4;
        driverService.selectCar(driverId, carDO);
        List<DriverDO> filter = driverService.filter(carDO);
        assertEquals(Integer.valueOf(1), Integer.valueOf(filter.size()));
    }
}
