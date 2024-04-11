package com.invoice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CompanyDTO {
    private Long id;

    private String name;

    private String email;

    private Instant creationDate;

}
