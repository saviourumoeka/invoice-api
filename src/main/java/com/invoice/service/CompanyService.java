package com.invoice.service;

import com.invoice.dto.request.CreateCompanyDTO;
import com.invoice.dto.response.CompanyDTO;

import java.util.List;

public interface CompanyService {

    CompanyDTO create(CreateCompanyDTO createCompanyDTO);

    CompanyDTO  get(Long id);

    List<CompanyDTO > getAll(int page, int size);

    CompanyDTO  update(Long id, CreateCompanyDTO createCompanyDTO);

    void delete(Long id);
}
