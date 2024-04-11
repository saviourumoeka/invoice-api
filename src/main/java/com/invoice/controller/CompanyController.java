package com.invoice.controller;

import com.invoice.config.ExtendedConstants;
import com.invoice.dto.request.CreateCompanyDTO;
import com.invoice.dto.response.DefaultApiResponse;
import com.invoice.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @PostMapping
    public ResponseEntity<DefaultApiResponse<?>> create(@Valid @RequestBody CreateCompanyDTO createCompanyDTO) {
        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(companyService.create(createCompanyDTO));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultApiResponse<?>> get(@PathVariable Long id) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(companyService.get(id));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<DefaultApiResponse<?>> getAll(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(companyService.getAll(page,size));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultApiResponse<?>> update(@PathVariable Long id, @Valid @RequestBody CreateCompanyDTO createCompanyDTO) {

        var apiResponse = new DefaultApiResponse<>();

        apiResponse.setData(companyService.update(id, createCompanyDTO));
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultApiResponse<?>> delete(@PathVariable Long id) {

        var apiResponse = new DefaultApiResponse<>();

        companyService.delete(id);

        apiResponse.setData(null);
        apiResponse.setStatus(ExtendedConstants.ResponseCode.SUCCESS.getStatus());
        apiResponse.setMessage(ExtendedConstants.ResponseCode.SUCCESS.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}
