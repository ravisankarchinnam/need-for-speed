package com.github.ravisankarchinnam.speed.dao;

import java.util.List;

import com.github.ravisankarchinnam.speed.enums.OnlineStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.github.ravisankarchinnam.speed.model.DriverDO;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>, JpaSpecificationExecutor<DriverDO>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);


    DriverDO findByUsername(String username);


    List<DriverDO> findByCar_Id(Long carId);
}
