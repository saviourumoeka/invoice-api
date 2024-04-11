package com.invoice.service;

import com.invoice.dto.request.CreateInvoiceDTO;
import com.invoice.dto.request.UpdateInvoiceDTO;
import com.invoice.dto.response.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO create(CreateInvoiceDTO createInvoiceDTO);

    InvoiceDTO  get(Long id);

    List<InvoiceDTO > getAll(int page, int size);

    List<InvoiceDTO > getAllByCompany( Long companyId,int page, int size);

    InvoiceDTO  update(Long id, UpdateInvoiceDTO updateInvoiceDTO);

    InvoiceDTO markAsPaid(Long id);

    String generateLink(Long id);


    void delete(Long id);
}
