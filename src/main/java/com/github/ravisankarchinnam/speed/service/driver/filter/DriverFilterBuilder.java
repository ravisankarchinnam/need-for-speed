package com.github.ravisankarchinnam.speed.service.driver.filter;

import com.github.ravisankarchinnam.speed.model.DriverDO;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Builder class which populates all filters.
 * <p/>
 */
public class DriverFilterBuilder
{

    public static List<Specification<DriverDO>> getFilters(DriverFilterParams driverFilterParams)
    {
        List<Filter<DriverDO>> filters = new ArrayList<>();
        filters.add(new OnlineStatusFilter(driverFilterParams));
        filters.add(new ConvertibleFilter(driverFilterParams));
        filters.add(new EngineTypeFilter(driverFilterParams));
        filters.add(new SeatCountFilter(driverFilterParams));

        return filters.stream().filter(f -> f.isApplicable()).map(f -> f.getFilter()).collect(Collectors.toList());
    }

}
