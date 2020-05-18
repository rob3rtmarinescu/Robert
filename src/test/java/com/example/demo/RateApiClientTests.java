package com.example.demo;

import com.example.demo.web.client.RateApiClient;


import com.example.demo.web.client.RatesDto;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.net.URISyntaxException;




@RunWith(MockitoJUnitRunner.class)
public class RateApiClientTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RateApiClient rateApiClient;

    @Test
    public void testDtoRestTemplate() throws URISyntaxException {
        final String baseUrl = "https://api.exchangeratesapi.io/latest";
        URI uri = new URI(baseUrl);

        ResponseEntity<RatesDto> result = restTemplate.getForEntity(uri, RatesDto.class);
    }
}
