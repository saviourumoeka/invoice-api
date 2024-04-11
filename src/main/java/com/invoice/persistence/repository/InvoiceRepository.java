package com.invoice.persistence.repository;

import com.invoice.persistence.entity.Company;
import com.invoice.persistence.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    Page<Invoice> findAllByCompany(Company company, Pageable pageable);
}
