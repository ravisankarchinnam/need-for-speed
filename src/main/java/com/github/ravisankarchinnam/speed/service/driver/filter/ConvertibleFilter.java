package com.github.ravisankarchinnam.speed.service.driver.filter;

import com.github.ravisankarchinnam.speed.model.DriverDO;
import org.springframework.data.jpa.domain.Specification;

/**
 * Filter for convertible property of Car entity.
 * <p/>
 */
public class ConvertibleFilter implements Filter<DriverDO>
{

    private DriverFilterParams driverFilterParams;


    public ConvertibleFilter(DriverFilterParams driverFilterParams)
    {
        this.driverFilterParams = driverFilterParams;
    }


    @Override
    public Specification<DriverDO> getFilter()
    {
        return (root, query, cb) -> cb.equal(root.join("car").get("convertible"), driverFilterParams.getConvertible());
    }


    @Override
    public Boolean isApplicable()
    {
        return driverFilterParams != null && driverFilterParams.getConvertible() != null;
    }

}
