package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface EquipmentRepository extends JpaRepository<tbEquipment, Long> {
    @Query(value = "SELECT * FROM tb_equipment "
         +   " WHERE device_type = :dType"
            ,nativeQuery = true)
    Collection<tbEquipment> findEquipmentQuantityAndAvailabilityByDeviceType(String dType);

    Optional<tbEquipment> findEquipmentBySerialNumber(String serialNo);
}
