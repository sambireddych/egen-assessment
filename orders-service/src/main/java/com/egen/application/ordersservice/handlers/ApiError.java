package com.egen.application.ordersservice.handlers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonSerialize
@Getter
@Setter
@RequiredArgsConstructor
public class ApiError {
    @JsonProperty
    private String detail;
    @JsonProperty
    private String source;
    @JsonProperty
    private String title;

    @Override
    public String toString() {
        return String.format("%s while calling %s Details: %s", title, source, detail);
    }
}
