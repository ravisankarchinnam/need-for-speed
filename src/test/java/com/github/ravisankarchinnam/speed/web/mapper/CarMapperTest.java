package com.github.ravisankarchinnam.speed.web.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.ravisankarchinnam.speed.dto.CarDTO;
import com.github.ravisankarchinnam.speed.model.CarDO;
import com.github.ravisankarchinnam.speed.enums.ConvertibleValue;
import com.github.ravisankarchinnam.speed.enums.EngineType;

public class CarMapperTest
{

    @Test
    public void testMakeCarDO()
    {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder();
        carDTOBuilder
            .setLicensePlate("ASDS WE 6577")
            .setSeatCount(4)
            .setRating(2)
            .setEngineType(EngineType.PETROL.type())
            .setConvertible(ConvertibleValue.YES.value());
        CarDTO createCarDTO = carDTOBuilder.createCarDTO();
        CarDO makeCarDO = CarMapper.makeCarDO(createCarDTO);
        assertEquals(createCarDTO.getLicensePlate(), makeCarDO.getLicensePlate());
        assertEquals(createCarDTO.getSeatCount(), makeCarDO.getSeatCount());
        assertEquals(Double.valueOf(createCarDTO.getRating()), Double.valueOf(makeCarDO.getRating()));
        assertEquals(createCarDTO.getEngineType(), makeCarDO.getEngineType());
        assertEquals(createCarDTO.getConvertible(), makeCarDO.getConvertible());
    }


    @Test
    public void testMakeCarDTO()
    {
        CarDO carDO = new CarDO("ASDS WE 6577", 5, 3, null);
        CarDTO makeCarDTO = CarMapper.makeCarDTO(carDO);
        assertEquals(carDO.getLicensePlate(), makeCarDTO.getLicensePlate());
        assertEquals(carDO.getSeatCount(), makeCarDTO.getSeatCount());
        assertEquals(Double.valueOf(carDO.getRating()), Double.valueOf(makeCarDTO.getRating()));
    }


    @Test
    public void testMakeDriverDTOList()
    {
        CarDO carDO = new CarDO("QA AB 333", 5, 3, null);
        CarDO carDO1 = new CarDO("QA CD 444", 5, 3, null);
        CarDO carDO2 = new CarDO("QA EF 555", 5, 3, null);
        ArrayList<CarDO> arrayList = new ArrayList<CarDO>();
        arrayList.add(carDO);
        arrayList.add(carDO1);
        arrayList.add(carDO2);
        List<CarDTO> makeDriverDTOList = CarMapper.makeDriverDTOList(arrayList);
        assertEquals(arrayList.size(), makeDriverDTOList.size());
    }

}
