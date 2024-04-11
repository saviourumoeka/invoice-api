package com.invoice.service.serviceImpl;

import com.invoice.dto.request.CreateInvoiceDTO;
import com.invoice.dto.request.UpdateInvoiceDTO;
import com.invoice.dto.response.InvoiceDTO;
import com.invoice.persistence.entity.Company;
import com.invoice.persistence.entity.Invoice;
import com.invoice.persistence.repository.CompanyRepository;
import com.invoice.persistence.repository.InvoiceRepository;
import com.invoice.service.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final CompanyRepository companyRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CompanyRepository companyRepository) {
        this.invoiceRepository = invoiceRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public InvoiceDTO create(CreateInvoiceDTO createInvoiceDTO) {

        Company company = companyRepository.findById(createInvoiceDTO.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));


        Invoice invoice = Invoice.builder()
                            .company(company)
                            .amount(createInvoiceDTO.getAmount())
                             .paid(false)
                            .issueDate(LocalDate.parse(createInvoiceDTO.getIssueDate()))
                            .dueDate(LocalDate.parse(createInvoiceDTO.getDueDate()))
                                .creationDate(Instant.now())
                                .build();

        invoice = invoiceRepository.save(invoice);


        return mapToDTO(invoice);
    }

    @Override
    public InvoiceDTO get(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        return mapToDTO(invoice);
    }

    @Override
    public List<InvoiceDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Invoice> invoices = invoiceRepository.findAll(pageable);

        return invoices.stream().map(this::mapToDTO).collect(Collectors.toList());

    }

    @Override
    public List<InvoiceDTO> getAllByCompany(Long companyId, int page, int size) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        Pageable pageable = PageRequest.of(page, size);

        Page<Invoice> invoices = invoiceRepository.findAllByCompany(company,pageable);

        return invoices.stream().map(this::mapToDTO).collect(Collectors.toList());

    }

    @Override
    public InvoiceDTO update(Long id, UpdateInvoiceDTO invoiceDTO) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        invoice.setAmount(invoiceDTO.getAmount());
        invoice.setIssueDate(LocalDate.parse(invoiceDTO.getIssueDate()));
        invoice.setDueDate(LocalDate.parse(invoiceDTO.getDueDate()));

        invoice = invoiceRepository.save(invoice);

        return mapToDTO(invoice);
    }

    @Override
    public InvoiceDTO markAsPaid(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        invoice.setPaid(true);

        invoice = invoiceRepository.save(invoice);

        return mapToDTO(invoice);
    }

    @Override
    public String generateLink(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        return "https://invoiceapp.io/company/"+ invoice.getCompany().getId()+"/invoice/"+invoice.getId();
    }


    @Override
    public void delete(Long id) {

        if(invoiceRepository.existsById(id)){
            invoiceRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Invoice not found");
        }

    }

    private InvoiceDTO mapToDTO(Invoice invoice) {
        return  InvoiceDTO.builder()
                .id(invoice.getId())
                .companyName(invoice.getCompany().getName())
                .amount(invoice.getAmount())
                .issueDate(invoice.getIssueDate())
                .dueDate(invoice.getDueDate())
                .paid(invoice.isPaid())
                .build();
    }
}
