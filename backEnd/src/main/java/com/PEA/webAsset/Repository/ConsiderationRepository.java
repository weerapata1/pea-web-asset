package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbConsideration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface ConsiderationRepository extends JpaRepository<tbConsideration,Long> {

    tbConsideration findByConsiderId(String considerId);
    tbConsideration findByQuotation(String Quotation);

//    tbConsideration findFileByID(String considerId)

}
