package com.github.ravisankarchinnam.speed.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.ravisankarchinnam.speed.MyAppBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ravisankarchinnam.speed.dto.ManufacturerDTO;

public class ManufacturerControllerTest extends MyAppBaseTest
{
    private String json = null;


    @Before
    public void createMockCarDTO() throws JsonProcessingException
    {
        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder =
            ManufacturerDTO
                .newBuilder()
                .setId(6L)
                .setManufacturer("Bentely");
        ObjectMapper mapper = new ObjectMapper();
        json = mapper.writeValueAsString(manufacturerDTOBuilder.createManufacturerDO());
    }


    @Test
    public void testGetManufacturerByIdWithoutSecurity() throws Exception
    {
        this.mockMvc.perform(get("/v1/manufacturers/1")).andExpect(status().isUnauthorized());
    }


    @Test
    public void testGetManufacturerByIdWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/manufacturers/1")
                    .with(authentication(auth)))
            .andExpect(status().isOk());
    }


    @Test
    public void testGetManufacturerByIdWithSecurityWithIncorrectId() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/manufacturers/91")
                    .with(authentication(auth)))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testGetManufacturerByNameWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/manufacturers?manufacturerName=Ferrari")
                    .with(authentication(auth)))
            .andExpect(status().isOk());
    }


    @Test
    public void testGetManufacturerByNameWithIncorrectName() throws Exception
    {
        this.mockMvc
            .perform(
                get("/v1/manufacturers?manufacturerName=Maserati")
                    .with(authentication(auth)))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void testCreateManufacturerWithSecurity() throws Exception
    {
        this.mockMvc
            .perform(
                post("/v1/manufacturers")
                    .with(authentication(auth))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(201));
    }
}
