package com.invoice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateInvoiceDTO {

    @NotNull(message = "Company Id must be provided")
    private Long companyId;

    @NotNull(message = "Amount must be provided")
    @Positive(message = "Amount must be positive")
    private double amount;

    @NotNull(message = "Issue date must be provided")
    private String issueDate;

    @NotNull(message = "Due date must be provided")
    private String dueDate;

}
