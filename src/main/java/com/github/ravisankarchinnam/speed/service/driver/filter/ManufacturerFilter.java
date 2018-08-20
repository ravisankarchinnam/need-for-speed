package com.github.ravisankarchinnam.speed.service.driver.filter;

import org.springframework.data.jpa.domain.Specification;

import com.github.ravisankarchinnam.speed.model.DriverDO;

/**
 * Filter for manufacturer property of Manufacturer entity.
 * <p/>
 */
public class ManufacturerFilter implements Filter<DriverDO>
{
    private DriverFilterParams driverFilterParams;


    public ManufacturerFilter(DriverFilterParams driverFilterParams)
    {
        this.driverFilterParams = driverFilterParams;
    }


    @Override
    public Specification<DriverDO> getFilter()
    {
        return (root, query, cb) -> cb.equal(root.join("car").join("manufacturer").get("manufacturer"), driverFilterParams.getManufacturer());
    }


    @Override
    public Boolean isApplicable()
    {
        return driverFilterParams != null && driverFilterParams.getEngineType() != null;
    }

}
