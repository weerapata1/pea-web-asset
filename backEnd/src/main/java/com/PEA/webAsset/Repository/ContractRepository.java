package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContractRepository extends JpaRepository<tbContract, Long> {


}