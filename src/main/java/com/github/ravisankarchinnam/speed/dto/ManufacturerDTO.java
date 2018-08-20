package com.github.ravisankarchinnam.speed.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManufacturerDTO
{

    @JsonIgnore
    private Long id;

    @NotNull(message = "manufacturer can not be null!")
    private String manufacturer;


    private ManufacturerDTO()
    {}


    private ManufacturerDTO(Long id, String manufacturer)
    {
        this.id = id;
        this.manufacturer = manufacturer;
    }


    public static ManufacturerDTOBuilder newBuilder()
    {
        return new ManufacturerDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getManufacturer()
    {
        return manufacturer;
    }

    public static class ManufacturerDTOBuilder
    {

        private Long id;
        private String manufacturer;


        public ManufacturerDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public ManufacturerDTOBuilder setManufacturer(String manufacturer)
        {
            this.manufacturer = manufacturer;
            return this;
        }


        public ManufacturerDTO createManufacturerDO()
        {
            return new ManufacturerDTO(id, manufacturer);
        }
    }

}
