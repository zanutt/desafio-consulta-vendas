package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new  com.devsuperior.dsmeta.dto.SaleReportDTO(obj.id, obj.amount, obj.date, obj.seller.name) " 
            +   "FROM Sale obj " 
            +   "JOIN obj.seller sel "  
            +   "WHERE UPPER(sel.name) LIKE UPPER(CONCAT('%',:name, '%')) "  
            +   "AND obj.date BETWEEN :minDate AND :maxDate"  
                )
    Page<SaleReportDTO> report(LocalDate minDate, LocalDate maxDate, String name, org.springframework.data.domain.Pageable pageable);

    @Query("SELECT new  com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name , SUM(obj.amount)) " 
            +   "FROM Sale obj "  
            +   "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:name, '%')) " 
            +   "AND obj.date BETWEEN :minDate AND :maxDate " 
            +   "GROUP BY obj.seller.name ")
    List<SaleSummaryDTO> summary(LocalDate minDate, LocalDate maxDate, String name);

}
