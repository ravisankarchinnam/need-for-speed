package com.github.ravisankarchinnam.speed.service.driver.filter;

import org.springframework.data.jpa.domain.Specification;

/**
 * Root Filter Interface should be implemented by all Filters.
 *
 * @param <T>
 */
public interface Filter<T>
{

    Specification<T> getFilter();


    Boolean isApplicable();

}
