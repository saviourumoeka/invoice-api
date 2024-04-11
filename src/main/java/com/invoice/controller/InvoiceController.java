package com.invoice.controller;

import com.invoice.config.ExtendedConstants;
import com.invoice.dto.request.CreateInvoiceDTO;
import com.invoice.dto.request.UpdateInvoiceDTO;
import com.invoice.dto.response.DefaultApiResponse;
import com.invoice.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @PostMapping
    public ResponseEntity<DefaultApiResponse<?>> create(@Valid @RequestBody CreateInvoiceDTO createInvoiceDTO) {
        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(invoiceService.create(createInvoiceDTO));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultApiResponse<?>> get(@PathVariable Long id) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(invoiceService.get(id));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}/link")
    public ResponseEntity<DefaultApiResponse<?>> generateLink(@PathVariable Long id) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(invoiceService.generateLink(id));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<DefaultApiResponse<?>> getAll(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(invoiceService.getAll(page,size));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<DefaultApiResponse<?>> getAllByCompany(@PathVariable Long companyId,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(invoiceService.getAllByCompany(companyId,page,size));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultApiResponse<?>> update(@PathVariable Long id, @Valid @RequestBody UpdateInvoiceDTO updateInvoiceDTO) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(invoiceService.update(id, updateInvoiceDTO));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PutMapping("/{id}/paid")
    public ResponseEntity<DefaultApiResponse<?>> markAsPaid(@PathVariable Long id) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(invoiceService.markAsPaid(id));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultApiResponse<?>> delete(@PathVariable Long id) {

        var apiResponse = new DefaultApiResponse<>();

        invoiceService.delete(id);

        apiResponse.setData(null);
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}
