package com.github.ravisankarchinnam.speed.service.driver.filter;

import com.github.ravisankarchinnam.speed.model.DriverDO;
import org.springframework.data.jpa.domain.Specification;

/**
 * Filter for engineType property of Car entity.
 * <p/>
 */
public class EngineTypeFilter implements Filter<DriverDO>
{

    private DriverFilterParams driverFilterParams;


    public EngineTypeFilter(DriverFilterParams driverFilterParams)
    {
        this.driverFilterParams = driverFilterParams;
    }


    @Override
    public Specification<DriverDO> getFilter()
    {
        return (root, query, cb) -> cb.equal(root.join("car").get("engineType"), driverFilterParams.getEngineType());
    }


    @Override
    public Boolean isApplicable()
    {
        return driverFilterParams != null && driverFilterParams.getEngineType() != null;
    }

}
