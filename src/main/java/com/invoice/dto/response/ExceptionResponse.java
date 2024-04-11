package com.invoice.dto.response;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ExceptionResponse <G>{
    private String msg;
    private Instant timeStamp;
    private Integer status;
    private List<G> data;
}
