package com.devsuperior.dsmeta.dto;

public class SaleSummaryDTO {
    private String sellerName;
    private Double totalSales;

    public SaleSummaryDTO(){

    }

    public SaleSummaryDTO(String sellerName, Double totalSales) {
        this.sellerName = sellerName;
        this.totalSales = totalSales;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotalSales() {
        return totalSales;
    }
}