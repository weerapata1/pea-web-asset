package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDeviceBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeviceBrandRepository extends JpaRepository<tbDeviceBrand , Long> {
}
