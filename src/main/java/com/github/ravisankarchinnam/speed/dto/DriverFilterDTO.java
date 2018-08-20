package com.github.ravisankarchinnam.speed.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.ravisankarchinnam.speed.enums.OnlineStatus;
import com.github.ravisankarchinnam.speed.service.driver.filter.DriverFilterParams;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverFilterDTO
{

    @JsonProperty
    private OnlineStatus onlineStatus;

    @JsonProperty
    private Integer seatCount;

    @JsonProperty
    private Boolean convertible;

    @JsonProperty
    private String engineType;

    @JsonProperty
    private String manufacturer;


    public DriverFilterDTO()
    {}


    public DriverFilterDTO(
        @JsonProperty OnlineStatus onlineStatus, @JsonProperty Integer seatCount,
        @JsonProperty Boolean convertible, @JsonProperty String engineType, @JsonProperty String manufacturer)
    {
        this.onlineStatus = onlineStatus;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
    }


    public OnlineStatus getOnlineStatus()
    {
        return onlineStatus;
    }


    public void setOnlineStatus(OnlineStatus onlineStatus)
    {
        this.onlineStatus = onlineStatus;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public void setConvertible(Boolean convertible)
    {
        this.convertible = convertible;
    }


    public String getEngineType()
    {
        return engineType;
    }


    public void setEngineType(String engineType)
    {
        this.engineType = engineType;
    }


    public String getManufacturer()
    {
        return manufacturer;
    }


    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }


    @JsonIgnore
    public DriverFilterParams toDriverFilterParams()
    {
        return new DriverFilterParams(onlineStatus, seatCount, convertible, engineType, manufacturer);
    }
}
