package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DataRepo extends JpaRepository<tbData, Long> {
    
}
