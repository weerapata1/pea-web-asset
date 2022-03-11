package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LocationRepository extends JpaRepository<tbLocation, Long> {
}
