package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepairRepository extends JpaRepository<tbRepair ,Long> {
}
