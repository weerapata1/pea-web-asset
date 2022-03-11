package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDatatable_format;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DatatableFormatRepository extends JpaRepository<tbDatatable_format, Long>{

}
