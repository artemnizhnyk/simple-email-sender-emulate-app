package com.artemnizhnyk.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private String error;

    @JsonProperty("error_description")
    private String errorDescription;
}
