package com.invoice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InvoiceDTO {
    private Long id;

    private String companyName;

    private double amount;

    private boolean paid;

    private LocalDate issueDate;

    private LocalDate dueDate;

}
