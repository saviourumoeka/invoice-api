package com.invoice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateCompanyDTO {

    @NotNull(message = "Name must be provided")
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9\\s'-]{3,}$")
    private String name;

    @NotNull(message = "Email must be provided")
    @Email(message = "Must be an Email")
    private String email;
}
