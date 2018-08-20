package com.github.ravisankarchinnam.speed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.github.ravisankarchinnam.speed.enums.EngineType;

/**
 * Car Domain object.
 * <p/>
 */
@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate", columnNames = {"licensePlate"}))
public class CarDO extends BaseDO
{

    @Column(nullable = false)
    @NotNull(message = "licensePlate can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "seatCount can not be null!")
    private int seatCount;

    @Column(nullable = false)
    private Boolean convertible = false;

    @Column(nullable = false)
    @NotNull(message = "rating can not be null!")
    private double rating;

    @Column(nullable = false)
    @NotNull(message = "engineType can not be null!")
    private String engineType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", updatable = false)
    private ManufacturerDO manufacturer;


    protected CarDO()
    {}


    public CarDO(String licensePlate, int seatCount, double rating, ManufacturerDO manufacturer)
    {
        this(licensePlate, seatCount, false, rating, EngineType.PETROL.type(), manufacturer);
    }


    public CarDO(String licensePlate, int seatCount, Boolean convertible, double rating, ManufacturerDO manufacturer)
    {
        this(licensePlate, seatCount, convertible, rating, EngineType.PETROL.type(), manufacturer);
    }


    public CarDO(
        String licensePlate, int seatCount, Boolean convertible, double rating, String engineType,
        ManufacturerDO manufacturer)
    {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturer = manufacturer;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public int getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(int seatCount)
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


    public double getRating()
    {
        return rating;
    }


    public void setRating(double rating)
    {
        this.rating = rating;
    }


    public String getEngineType()
    {
        return engineType;
    }


    public void setEngineType(String engineType)
    {
        this.engineType = engineType;
    }


    public ManufacturerDO getManufacturer()
    {
        return manufacturer;
    }


    public void setManufacturer(ManufacturerDO manufacturer)
    {
        this.manufacturer = manufacturer;
    }

}
