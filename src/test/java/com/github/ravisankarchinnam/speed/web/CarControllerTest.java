package com.github.ravisankarchinnam.speed.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.ravisankarchinnam.speed.MyAppBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ravisankarchinnam.speed.dto.CarDTO;
import com.github.ravisankarchinnam.speed.enums.ConvertibleValue;
import com.github.ravisankarchinnam.speed.enums.EngineType;

public class CarControllerTest extends MyAppBaseTest
{
    String json = null;
    private static final ObjectMapper mapper = new ObjectMapper();


    @Before
    public void createMockCarDTO() throws JsonProcessingException
    {
        CarDTO.CarDTOBuilder carDTOBuilder =
            CarDTO
                .newBuilder()
                .setLicensePlate("JII A123 345")
                .setSeatCount(4)
                .setRating(2)
                .setEngineType(EngineType.PETROL.type())
                .setConvertible(ConvertibleValue.YES.value())
                .setManufacturer(null);
        json = mapper.writeValueAsString(carDTOBuilder.createCarDTO());
    }


    @Test
    public void testGetCarByIdWithoutSecurity() throws Exception
    {
        this.mockMvc.perform(get("/v1/cars/1")).andExpect(status().isUnauthorized());
    }


    @Test
    public void testGetCarByIdWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars/1")
                    .with(authentication(auth)))
            .andExpect(status().isOk());
    }


    @Test
    public void testGetCarByIdWithSecurityWithWrongId() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars/20")
                    .with(authentication(auth)))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testFindByEngineTypeWithoutSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars")
                    .param("engineType", EngineType.PETROL.type()))
            .andExpect(status().isUnauthorized());
    }


    @Test
    public void testFindByEngineTypeWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars")
                    .with(authentication(auth))
                    .param("engineType", EngineType.PETROL.toString()))
            .andExpect(status().isOk());
    }


    @Test
    public void testFindByEngineTypeWithSecurityWithWrongValue() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars")
                    .with(authentication(auth))
                    .param("engineType", "combustion"))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testFindByConvertibleWithoutSecurity() throws Exception
    {
        this.mockMvc
            .perform(get("/v1/cars/convertible/".concat(ConvertibleValue.YES.toString())))
            .andExpect(status().isUnauthorized());
    }


    @Test
    public void testFindByConvertibleWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars/convertible/".concat(ConvertibleValue.YES.toString()))
                    .with(authentication(auth)))
            .andExpect(status().isOk());
    }


    @Test
    public void testFindByConvertibleWithSecurityWithWrongValue() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars/convertible/")
                    .with(authentication(auth)))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testFindByManufacturerWithoutSecurity() throws Exception
    {
        this.mockMvc
            .perform(get("/v1/cars/manufacturer/".concat("Mercedes")))
            .andExpect(status().isUnauthorized());
    }


    @Test
    public void testFindByManufacturerWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars/manufacturer/".concat("Mercedes"))
                    .with(authentication(auth)))
            .andExpect(status().isOk());
    }


    @Test
    public void testFindByManufacturerWithSecurityWithWrongValue() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/cars/manufacturer/")
                    .with(authentication(auth)))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testCreateCarWithoutSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                post("/v1/cars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isUnauthorized());
    }


    @Test
    public void testCreateCarWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                post("/v1/cars")
                    .with(authentication(auth))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(201));
    }


    @Test
    public void testCreateCarWithSecurityWithWrongValue() throws Exception
    {
        CarDTO.CarDTOBuilder carDTOBuilder =
            CarDTO
                .newBuilder()
                .setLicensePlate("MD IND 324")
                .setSeatCount(4)
                .setRating(2)
                .setManufacturer(null);
        String json = mapper.writeValueAsString(carDTOBuilder.createCarDTO());
        this.mockMvc
            .perform(
                post("/v1/cars")
                    .with(authentication(auth))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testDeleteCarWithoutSecurity() throws Exception
    {
        this.mockMvc.perform(delete("/v1/cars/1")).andExpect(status().isUnauthorized());
    }


    @Test
    public void testDeleteCarWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                delete("/v1/cars/1")
                    .with(authentication(auth)))
            .andExpect(status().isOk());
    }


    @Test
    public void testDeleteCarWithSecurityWithWrongId() throws Exception
    {
        this.mockMvc
            .perform(
                delete("/v1/cars/143")
                    .with(authentication(auth)))
            .andExpect(status().isBadRequest());
    }
}
