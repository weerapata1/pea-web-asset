package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<tbDevice, Long> {

    @Query(value = "SELECT * FROM tb_device t",nativeQuery = true)
    Collection<tbDevice> findAllDevicesNative();

}