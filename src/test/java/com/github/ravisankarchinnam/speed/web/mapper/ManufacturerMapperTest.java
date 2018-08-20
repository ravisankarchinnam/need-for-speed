package com.github.ravisankarchinnam.speed.web.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.ravisankarchinnam.speed.dto.ManufacturerDTO;
import com.github.ravisankarchinnam.speed.model.ManufacturerDO;

public class ManufacturerMapperTest
{

    @Test
    public void testMakeManufacturerDO()
    {
        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder =
            ManufacturerDTO
                .newBuilder()
                .setId(6L)
                .setManufacturer("Bentely");
        ManufacturerDTO createManufacturerDTO = manufacturerDTOBuilder.createManufacturerDO();
        ManufacturerDO makeManufacturerDO = ManufacturerMapper.makeManufacturerDO(createManufacturerDTO);
        assertEquals(createManufacturerDTO.getManufacturer(), makeManufacturerDO.getManufacturer());
    }


    @Test
    public void testMakeManufacturerDTO()
    {
        ManufacturerDO manufacturerDO = new ManufacturerDO("SCODA");
        ManufacturerDTO makeManufacturerDTO = ManufacturerMapper.makeManufacturerDTO(manufacturerDO);
        assertEquals(manufacturerDO.getManufacturer(), makeManufacturerDTO.getManufacturer());
    }


    @Test
    public void testMakeManufacturerDTOList()
    {
        ManufacturerDO manufacturerDO = new ManufacturerDO("Toyota");
        ManufacturerDO manufacturerDO1 = new ManufacturerDO("Suziki");
        ManufacturerDO manufacturerDO2 = new ManufacturerDO("Bajaj");
        ArrayList<ManufacturerDO> manufacturers = new ArrayList<ManufacturerDO>();
        manufacturers.add(manufacturerDO);
        manufacturers.add(manufacturerDO1);
        manufacturers.add(manufacturerDO2);
        List<ManufacturerDTO> makeDriverDTOList = ManufacturerMapper.makeManufacturerList(manufacturers);
        assertEquals(manufacturers.size(), makeDriverDTOList.size());
    }
}
