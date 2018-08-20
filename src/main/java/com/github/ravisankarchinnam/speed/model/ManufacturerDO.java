package com.github.ravisankarchinnam.speed.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Manufacturer Domain Object.
 * <p/>
 */
@Entity
@Table(
    name = "manufacturer", uniqueConstraints = @UniqueConstraint(
        name = "uc_manufacturer", columnNames = {
            "manufacturer"}))
public class ManufacturerDO extends BaseDO
{

    @Column(nullable = false)
    @NotNull(message = "manufacturer can not be null!")
    private String manufacturer;


    protected ManufacturerDO()
    {}


    public ManufacturerDO(String manufacturer)
    {
        super();
        this.manufacturer = manufacturer;
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
