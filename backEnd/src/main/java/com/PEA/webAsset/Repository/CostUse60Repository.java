package com.PEA.webAsset.Repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.PEA.webAsset.Entity.tbCostUse60;
import com.PEA.webAsset.Interface.Cost60Interface;
import com.PEA.webAsset.dto.Cost60ByMonthDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@RepositoryRestResource
public interface CostUse60Repository extends JpaRepository<tbCostUse60, Long> {

    @Query(value = "SELECT DATE_FORMAT(STR_TO_DATE(posting_date, '%d.%m.%Y'), '%Y-%m') AS yearMonth, " +
            "       SUM(value_co_curr) AS valuePerMonth, " +
            "       COUNT(*)           AS recordsPerMonth " +
            "FROM tb_cost_use60 " +
            "GROUP BY yearMonth " +
            "ORDER BY yearMonth", countQuery = "SELECT COUNT(*) FROM ( " +
                    "  SELECT 1 " +
                    "  FROM tb_cost_use60 " +
                    "  GROUP BY DATE_FORMAT(STR_TO_DATE(posting_date, '%d.%m.%Y'), '%Y-%m') " +
                    ") x", nativeQuery = true)
    Page<Cost60Interface.Cost60ByMonth> cost60GroupByMonth(Pageable pageable);

    @Query(value = "SELECT username, " +
            "       SUM(value_co_curr) AS valuePerUsername, " +
            "       COUNT(*)           AS recordsPerUsername " +
            "FROM tb_cost_use60 " +
            "GROUP BY username ", countQuery = "SELECT COUNT(*) FROM ( " +
                    "  SELECT 1 " +
                    "  FROM tb_cost_use60 " +
                    "  GROUP BY username " +
                    ") x", nativeQuery = true)
    Page<Cost60Interface.Cost60ByUser> cost60GroupByUser(Pageable pageable);
}
