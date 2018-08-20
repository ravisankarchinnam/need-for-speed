package com.github.ravisankarchinnam.speed.web.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.github.ravisankarchinnam.speed.helper.GeoCoordinate;
import com.github.ravisankarchinnam.speed.dto.DriverDTO;
import com.github.ravisankarchinnam.speed.model.CarDO;
import com.github.ravisankarchinnam.speed.model.DriverDO;

public class DriverMapper
{
    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword(), null);
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder =
            DriverDTO
                .newBuilder().setId(driverDO.getId())
                .setPassword(driverDO.getPassword()).setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }
        CarDO carDO = driverDO.getCar();
        driverDTOBuilder.setCarId(carDO != null ? carDO.getId() : null);

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream().map(DriverMapper::makeDriverDTO).collect(Collectors.toList());
    }
}
