package com.invoice.service.serviceImpl;

import com.invoice.dto.request.CreateCompanyDTO;
import com.invoice.dto.response.CompanyDTO;
import com.invoice.persistence.entity.Company;
import com.invoice.persistence.repository.CompanyRepository;
import com.invoice.service.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDTO create(CreateCompanyDTO createCompanyDTO) {

        Company company = Company.builder()
                                .name(createCompanyDTO.getName())
                                .email(createCompanyDTO.getEmail())
                                .creationDate(Instant.now())
                                .build();

        company = companyRepository.save(company);


        return mapToDTO(company);
    }

    @Override
    public CompanyDTO get(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        return mapToDTO(company);
    }

    @Override
    public List<CompanyDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Company> companies = companyRepository.findAll(pageable);

        return companies.stream().map(this::mapToDTO).collect(Collectors.toList());

    }

    @Override
    public CompanyDTO update(Long id, CreateCompanyDTO createCompanyDTO) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        company.setName(createCompanyDTO.getName());
        company.setEmail(createCompanyDTO.getEmail());

        company = companyRepository.save(company);

        return mapToDTO(company);
    }



    @Override
    public void delete(Long id) {

        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Company not found");
        }

    }

    private CompanyDTO mapToDTO(Company company) {
        return  CompanyDTO.builder()
                .id(company.getId())
                .email(company.getEmail())
                .name(company.getName())
                .creationDate(company.getCreationDate())
                .build();
    }
}
