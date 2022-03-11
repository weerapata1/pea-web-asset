package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbRepairStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface RepairStatusRepository extends JpaRepository<tbRepairStatus ,Integer> {
}
