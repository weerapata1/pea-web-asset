package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbCommitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommitmentRepository extends JpaRepository<tbCommitment, Long> {


}