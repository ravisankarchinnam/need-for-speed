package com.github.ravisankarchinnam.speed.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.ravisankarchinnam.speed.MyAppBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ravisankarchinnam.speed.dto.DriverFilterDTO;

/**
 * unit testing for DriverController for methods selectCar, deSelectCar and filter
 * @author azmym
 *
 */
public class DriverControllerTest extends MyAppBaseTest
{

    private String json = null;


    @Before
    public void createMockDriverFilterDTO() throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        DriverFilterDTO driverFilterDTO = new DriverFilterDTO();
        json = mapper.writeValueAsString(driverFilterDTO);
    }


    @Test
    public void testSelectCarWithoutSecurity() throws Exception
    {
        mockMvc
            .perform(
                put("/v1/drivers/selectcar")
                    .param("driverId", "1")
                    .param("carId", "1"))
            .andExpect(status().isUnauthorized())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testSelectCarWithSecurity() throws Exception
    {
        mockMvc
            .perform(
                put("/v1/drivers/selectcar")
                    .with(authentication(auth))
                    .param("driverId", "4")
                    .param("carId", "3"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void testSelectCarWithSecurityWithOfflineDriver() throws Exception
    {
        mockMvc
            .perform(
                put("/v1/drivers/selectcar")
                    .with(authentication(auth))
                    .param("driverId", "1")
                    .param("carId", "1"))
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testSelectCarWithSecurityWithUsedCar() throws Exception
    {
        mockMvc
            .perform(
                put("/v1/drivers/selectcar")
                    .with(authentication(auth))
                    .param("driverId", "5")
                    .param("carId", "3"))
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testDeSelectCarWithoutSecurity() throws Exception
    {
        mockMvc
            .perform(
                put("/v1/drivers/deselectcar")
                    .param("driverId", "1"))
            .andExpect(status().isUnauthorized())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testDeSelectCarWithSecurity() throws Exception
    {
        mockMvc
            .perform(
                put("/v1/drivers/deselectcar")
                    .with(authentication(auth))
                    .param("driverId", "4"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testGetFilterWithoutSecurity() throws Exception
    {
        mockMvc
            .perform(get("/v1/drivers/filter"))
            .andExpect(status().isUnauthorized())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testGetFilterWithSecurity() throws Exception
    {
        mockMvc
            .perform(
                get("/v1/drivers/filter")
                    .with(authentication(auth))
                    .param("licensePlate", "WW GG 544"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testPostFilterWithoutSecurity() throws Exception
    {
        mockMvc
            .perform(post("/v1/drivers/filter"))
            .andExpect(status().isUnauthorized())
            .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testPostFilterWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                post("/v1/drivers/filter")
                    .with(authentication(auth))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
