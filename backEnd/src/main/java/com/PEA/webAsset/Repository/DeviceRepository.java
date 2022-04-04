package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<tbDevice, Long> {

    @Query(value = "SELECT * from tb_device t WHERE t.cc_id = :cc_id", nativeQuery = true)
    Collection<tbDevice> findAllByCCId(
            @Param("cc_id") String cc_id);

    @Query(value = "SELECT d FROM tb_device d"
            , nativeQuery = false)
    Collection<tbDevice> findByCCCode(
//            @Param("cc_code")String cc_code
    );
}