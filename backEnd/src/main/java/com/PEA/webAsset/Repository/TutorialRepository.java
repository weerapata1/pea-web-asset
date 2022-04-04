package com.PEA.webAsset.Repository;


import com.PEA.webAsset.Entity.Tutorial;
import com.PEA.webAsset.Entity.tbDeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {


//    @Modifying
//    @Query(value = "INSERT INTO tutorials (id, title, description, published) " +
//            "values (:id , :title ,:description ,:published)", nativeQuery = true)
//    void insertData(@Param("id") Long id, @Param("title") String title, @Param("description") String description,
//                    @Param("published") Boolean published);

}
