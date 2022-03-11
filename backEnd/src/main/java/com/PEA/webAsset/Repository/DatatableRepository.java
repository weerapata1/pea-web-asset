package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDatatable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DatatableRepository extends JpaRepository<tbDatatable, Long>{
    
}
