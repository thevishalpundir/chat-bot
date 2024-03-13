package com.pilog.url.redirector.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiDetailsDTO {
    private String apiName;
    private String queryType;
    private String apiUrl;
}
