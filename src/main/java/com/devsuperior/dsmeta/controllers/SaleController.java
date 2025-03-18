package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;


@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
		@RequestParam(name = "minDate", defaultValue="") String minDate,
		@RequestParam(name = "maxDate", defaultValue="") String maxDate,
		@RequestParam(name = "name", defaultValue="") String name,
		Pageable pageable) {
		Page<SaleReportDTO> dto = service.report(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<?> getSummary(@RequestParam(name = "minDate", defaultValue="") String minDate,
		@RequestParam(name = "maxDate", defaultValue="") String maxDate,
		@RequestParam(name = "name", defaultValue="") String name) {
		List<SaleSummaryDTO> summary = service.summary(minDate, maxDate, name);
		return ResponseEntity.ok(summary);
	}
}
