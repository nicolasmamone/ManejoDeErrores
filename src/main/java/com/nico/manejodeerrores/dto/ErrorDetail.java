package com.nico.manejodeerrores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private Long timeStamp;
    private String developerMessage; //mensaje personalizado

    private Map<String, List<ValidationError>> errors = new HashMap<>();

}
