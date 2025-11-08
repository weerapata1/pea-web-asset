package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActiveStatusRepository extends JpaRepository<tbActiveStatus, Long> {
    tbActiveStatus findActiveStatusByActiveId(Long activeId);

}
