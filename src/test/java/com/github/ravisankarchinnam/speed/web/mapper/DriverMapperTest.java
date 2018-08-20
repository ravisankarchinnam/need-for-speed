package com.github.ravisankarchinnam.speed.web.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.github.ravisankarchinnam.speed.MyAppBaseTest;
import org.junit.Test;

import com.github.ravisankarchinnam.speed.dto.DriverDTO;
import com.github.ravisankarchinnam.speed.model.DriverDO;

public class DriverMapperTest extends MyAppBaseTest
{

    @Test
    public void testMakeDriverDO()
    {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder();
        driverDTOBuilder.setUsername("test").setPassword("test");
        DriverDTO driverDTO = driverDTOBuilder.createDriverDTO();
        DriverDO makeDriverDO = DriverMapper.makeDriverDO(driverDTO);
        assertEquals(driverDTO.getUsername(), makeDriverDO.getUsername());
        assertEquals(driverDTO.getPassword(), makeDriverDO.getPassword());

    }


    @Test
    public void testMakeDriverDTO()
    {
        DriverDO driverDO = new DriverDO("test", "test", null);
        DriverDTO makeDriverDTO = DriverMapper.makeDriverDTO(driverDO);
        assertEquals(driverDO.getUsername(), makeDriverDTO.getUsername());
        assertEquals(driverDO.getPassword(), makeDriverDTO.getPassword());
    }


    @Test
    public void testMakeDriverDTOList()
    {
        DriverDO driverDO = new DriverDO("test", "test", null);
        DriverDO driverDO1 = new DriverDO("test1", "test1", null);
        DriverDO driverDO2 = new DriverDO("test2", "test2", null);
        DriverDO driverDO3 = new DriverDO("test3", "test3", null);
        ArrayList<DriverDO> arrayList = new ArrayList<DriverDO>();
        arrayList.add(driverDO);
        arrayList.add(driverDO1);
        arrayList.add(driverDO2);
        arrayList.add(driverDO3);
        List<DriverDTO> makeDriverDTOList = DriverMapper.makeDriverDTOList(arrayList);
        assertEquals(arrayList.size(), makeDriverDTOList.size());
    }
}
