package com.github.ravisankarchinnam.speed.service.driver.filter;

import com.github.ravisankarchinnam.speed.model.DriverDO;
import org.springframework.data.jpa.domain.Specification;

/**
 * Filter for seatCount property of Car entity.
 * <p/>
 */
public class SeatCountFilter implements Filter<DriverDO>
{

    private DriverFilterParams driverFilterParams;


    public SeatCountFilter(DriverFilterParams driverFilterParams)
    {
        this.driverFilterParams = driverFilterParams;
    }


    @Override
    public Specification<DriverDO> getFilter()
    {
        return (root, query, cb) -> cb.greaterThan(root.join("car").get("seatCount"), driverFilterParams.getSeatCount());
    }


    @Override
    public Boolean isApplicable()
    {
        return driverFilterParams != null && driverFilterParams.getSeatCount() != null;
    }

}
