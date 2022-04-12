package com.PEA.webAsset.Repository;

import com.PEA.webAsset.Entity.tbDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<tbDevice, Long> {

    @Query(value = "SELECT * from tb_device t WHERE t.cc_id = :cc_id", nativeQuery = true)
    Collection<tbDevice> findAllByCCId(
            @Param("cc_id") String cc_id);

    @Query(value = "SELECT d FROM tb_device d"
            , nativeQuery = false)
    Collection<tbDevice> findByCCCode(
//            @Param("cc_code")String cc_code
    );
//    Page<tbDevice> findByDevSerialNo(String dev_serialNo,Pageable pageable);

    @Query(value = "SELECT * FROM tb_device t WHERE t.dev_serial_no like %:test1%",nativeQuery = true)
    Page<tbDevice> findAllByPattern(@Param("test1") String test1,Pageable pageable);
}
