package com.invoice;


import com.invoice.dto.request.CreateCompanyDTO;
import com.invoice.dto.response.CompanyDTO;
import com.invoice.persistence.entity.Company;
import com.invoice.persistence.repository.CompanyRepository;
import com.invoice.service.serviceImpl.CompanyServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompanyTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyService).build();
    }

    @Test
    public void testCreateCompany() {

        CreateCompanyDTO createCompanyDTO = new CreateCompanyDTO();
        createCompanyDTO.setName("Test Company");
        createCompanyDTO.setEmail("test@example.com");

        Company company = new Company();
        company.setName(createCompanyDTO.getName());
        company.setEmail(createCompanyDTO.getEmail());

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        CompanyDTO result = companyService.create(createCompanyDTO);

        assertNotNull(result);
        assertEquals(createCompanyDTO.getName(), result.getName());
        assertEquals(createCompanyDTO.getEmail(), result.getEmail());
    }

    @Test
    public void testGetCompany() {

        Long companyId = 1L;
        Company company = new Company();
        company.setId(companyId);
        company.setName("Test Company");
        company.setEmail("test@example.com");

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));


        CompanyDTO result = companyService.get(companyId);


        assertNotNull(result);
        assertEquals(companyId, result.getId());
        assertEquals(company.getName(), result.getName());
        assertEquals(company.getEmail(), result.getEmail());
    }

    @Test
    public void testGetAllCompanies() {

        int page = 1;
        int size = 10;
        List<Company> companies = Collections.singletonList(new Company());
        when(companyRepository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(companies));


        List<CompanyDTO> result = companyService.getAll(page, size);


        assertNotNull(result);
        assertEquals(companies.size(), result.size());

    }

    @Test
    public void testUpdateCompany() {

        Long companyId = 1L;
        CreateCompanyDTO updateCompanyDTO = new CreateCompanyDTO();
        updateCompanyDTO.setName("Updated Company");
        updateCompanyDTO.setEmail("updated@example.com");

        Company existingCompany = new Company();
        existingCompany.setId(companyId);
        existingCompany.setName("Test Company");
        existingCompany.setEmail("test@example.com");

        Company updatedCompany = new Company();
        updatedCompany.setId(companyId);
        updatedCompany.setName(updateCompanyDTO.getName());
        updatedCompany.setEmail(updateCompanyDTO.getEmail());

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.save(any(Company.class))).thenReturn(updatedCompany);


        CompanyDTO result = companyService.update(companyId, updateCompanyDTO);

        assertNotNull(result);
        assertEquals(companyId, result.getId());
        assertEquals(updateCompanyDTO.getName(), result.getName());
        assertEquals(updateCompanyDTO.getEmail(), result.getEmail());
    }

    @Test
    public void testDeleteCompany_NotFound() {

        Long companyId = 1L;


        when(companyRepository.existsById(companyId)).thenReturn(false);


        assertThrows(EntityNotFoundException.class, () -> companyService.delete(companyId));
    }

    @Test
    public void testDeleteCompany() {

        Long companyId = 1L;

        when(companyRepository.existsById(companyId)).thenReturn(true);

        companyService.delete(companyId);

        verify(companyRepository, times(1)).deleteById(companyId);
    }

}
