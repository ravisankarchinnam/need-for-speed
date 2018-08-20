package com.github.ravisankarchinnam.speed.web.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.github.ravisankarchinnam.speed.dto.CarDTO;
import com.github.ravisankarchinnam.speed.dto.ManufacturerDTO;
import com.github.ravisankarchinnam.speed.model.CarDO;
import com.github.ravisankarchinnam.speed.model.ManufacturerDO;

public class CarMapper
{

    public static CarDO makeCarDO(CarDTO carDTO)
    {
        ManufacturerDO manufacturerDO = null;
        if (null != carDTO.getManufacturer())
        {
            if (null != carDTO.getManufacturer().getManufacturer())
            {
                manufacturerDO = new ManufacturerDO(carDTO.getManufacturer().getManufacturer());
            }
        }
        return new CarDO(
            carDTO.getLicensePlate(), carDTO.getSeatCount(), carDTO.getConvertible(), carDTO.getRating(),
            carDTO.getEngineType(), manufacturerDO);
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carDTOBuilder =
            CarDTO
                .newBuilder().setId(carDO.getId())
                .setLicensePlate(carDO.getLicensePlate()).setConvertible(carDO.getConvertible())
                .setRating(carDO.getRating()).setSeatCount(carDO.getSeatCount()).setEngineType(carDO.getEngineType());

        ManufacturerDO manufacturer = carDO.getManufacturer();
        if (null != manufacturer)
        {
            ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder =
                ManufacturerDTO
                    .newBuilder()
                    .setId(carDO.getManufacturer().getId()).setManufacturer(carDO.getManufacturer().getManufacturer());
            carDTOBuilder.setManufacturer(manufacturerDTOBuilder.createManufacturerDO());
        }
        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeDriverDTOList(Collection<CarDO> cars)
    {
        return cars.stream().map(CarMapper::makeCarDTO).collect(Collectors.toList());
    }

}
