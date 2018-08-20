package com.github.ravisankarchinnam.speed.service.driver.filter;

import com.github.ravisankarchinnam.speed.model.DriverDO;
import org.springframework.data.jpa.domain.Specification;

/**
 * Filter for onlineStatus property of Driver entity.
 * <p/>
 */
public class OnlineStatusFilter implements Filter<DriverDO>
{

    private DriverFilterParams driverFilterParams;


    public OnlineStatusFilter(DriverFilterParams driverFilterParams)
    {
        this.driverFilterParams = driverFilterParams;
    }


    @Override
    public Specification<DriverDO> getFilter()
    {
        return (root, query, cb) -> cb.equal(root.get("onlineStatus"), driverFilterParams.getOnlineStatus());
    }


    @Override
    public Boolean isApplicable()
    {
        return driverFilterParams != null && driverFilterParams.getOnlineStatus() != null;
    }

}
