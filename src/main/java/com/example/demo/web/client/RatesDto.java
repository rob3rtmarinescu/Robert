package com.example.demo.web.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatesDto {

    private Map<String, Double> rates;
    private String base;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
