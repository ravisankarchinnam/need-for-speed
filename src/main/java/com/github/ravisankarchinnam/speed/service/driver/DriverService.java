package com.github.ravisankarchinnam.speed.service.driver;

import java.util.List;

import com.github.ravisankarchinnam.speed.model.CarDO;
import com.github.ravisankarchinnam.speed.model.DriverDO;
import com.github.ravisankarchinnam.speed.enums.OnlineStatus;
import com.github.ravisankarchinnam.speed.exception.CarAlreadyInUseException;
import com.github.ravisankarchinnam.speed.exception.ConstraintsViolationException;
import com.github.ravisankarchinnam.speed.exception.DriverOfflineException;
import com.github.ravisankarchinnam.speed.exception.EntityNotFoundException;
import com.github.ravisankarchinnam.speed.service.driver.filter.DriverFilterParams;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;


    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;


    void delete(Long driverId) throws EntityNotFoundException;


    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;


    List<DriverDO> find(OnlineStatus onlineStatus);


    void selectCar(long driverId, CarDO carDO) throws EntityNotFoundException, DriverOfflineException, CarAlreadyInUseException;


    void deSelectCar(long driverId) throws EntityNotFoundException;


    DriverDO findByUsername(String username);


    public List<DriverDO> filter(CarDO carDO);


    List<DriverDO> find(DriverFilterParams driverFilterParams);
}
