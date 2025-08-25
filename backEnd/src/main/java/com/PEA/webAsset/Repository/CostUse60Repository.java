package com.PEA.webAsset.Repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.PEA.webAsset.Entity.tbCostUse60;

import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource
public interface CostUse60Repository extends JpaRepository<tbCostUse60, Long> {
    
}
