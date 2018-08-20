package com.github.ravisankarchinnam.speed.service.driver.filter;

import com.github.ravisankarchinnam.speed.enums.OnlineStatus;

/**
 * Holds all the properties that can be used in filter pattern.
 * <p/>
 */
public class DriverFilterParams
{

    private OnlineStatus onlineStatus;

    private Integer seatCount;

    private Boolean convertible;

    private String engineType;

    private String manufacturer;


    public DriverFilterParams(OnlineStatus onlineStatus, Integer seatCount, Boolean convertible, String engineType, String manufacturer)
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


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public String getEngineType()
    {
        return engineType;
    }


    public String getManufacturer()
    {
        return manufacturer;
    }


    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

}
