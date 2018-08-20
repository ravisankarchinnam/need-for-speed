package com.github.ravisankarchinnam.speed.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{

    @JsonIgnore
    private Long id;

    @NotNull(message = "licensePlate can not be null!")
    private String licensePlate;

    @NotNull(message = "seatCount can not be null!")
    private int seatCount;

    private Boolean convertible;

    @NotNull(message = "rating can not be null!")
    private double rating;

    @NotNull(message = "engineType can not be null!")
    private String engineType;

    private ManufacturerDTO manufacturer;


    private CarDTO()
    {}


    private CarDTO(
        Long id, String licensePlate, int seatCount, Boolean convertible, double rating, String engineType,
        ManufacturerDTO manufacturer)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public int getSeatCount()
    {
        return seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public double getRating()
    {
        return rating;
    }


    public String getEngineType()
    {
        return engineType;
    }


    public ManufacturerDTO getManufacturer()
    {
        return manufacturer;
    }

    public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;
        private int seatCount;
        private Boolean convertible;
        private double rating;
        private String engineType;
        private ManufacturerDTO manufacturer;


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTOBuilder setSeatCount(int seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }


        public CarDTOBuilder setConvertible(Boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }


        public CarDTOBuilder setRating(double rating)
        {
            this.rating = rating;
            return this;
        }


        public CarDTOBuilder setEngineType(String engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarDTOBuilder setManufacturer(ManufacturerDTO manufacturer)
        {
            this.manufacturer = manufacturer;
            return this;
        }


        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, seatCount, convertible, rating, engineType, manufacturer);
        }

    }

}
