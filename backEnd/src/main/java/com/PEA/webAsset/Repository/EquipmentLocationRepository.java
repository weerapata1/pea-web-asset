package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbEquipmentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "*")

public interface EquipmentLocationRepository extends JpaRepository<tbEquipmentLocation, Long> {
    Collection<tbEquipmentLocation> findLocationNameById(Long id);

    tbEquipmentLocation findLocById(Long id);

}
