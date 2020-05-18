package com.example.demo.web.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RateApiClient {

    private final static String API_PATH = "https://api.exchangeratesapi.io/latest";

    private final RestTemplate restTemplate;


    public RatesDto getDataFromInternet() {
        URI uri = URI.create(API_PATH);
        return this.restTemplate.getForObject(uri, RatesDto.class);
    }
}
