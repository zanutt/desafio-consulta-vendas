package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public Page<SaleReportDTO> report(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate maxDateConvertida = (maxDate != null && !maxDate.isEmpty()) ? LocalDate.parse(maxDate) : today;
		LocalDate minDateConvertida = (minDate != null && !minDate.isEmpty()) ? LocalDate.parse(minDate) : maxDateConvertida.minusYears(1L);

		return repository.report(minDateConvertida, maxDateConvertida, name, pageable);
    }

    public List<SaleSummaryDTO> summary(String minDate, String maxDate, String name) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate maxDateConvertida = (maxDate != null && !maxDate.isBlank()) ? LocalDate.parse(maxDate) : today;
        LocalDate minDateConvertida = (minDate != null && !minDate.isBlank()) ? LocalDate.parse(minDate) : maxDateConvertida.minusYears(1L);

        return repository.summary(minDateConvertida, maxDateConvertida, name);
    }

}
