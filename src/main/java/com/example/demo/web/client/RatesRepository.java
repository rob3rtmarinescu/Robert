package com.example.demo.web.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatesRepository {

    private final RateApiClient rateApiClient;

    public RatesDto getCachedRates() {
        return cachedRates;
    }

    private RatesDto cachedRates;

    @Cacheable("rates")
    @HystrixCommand(fallbackMethod = "callApiForData")
    public RatesDto getRates() {
        if(this.cachedRates == null){
            return callApiForData();
        }
        return this.cachedRates;
    }

    public RatesDto callApiForData() {

        this.cachedRates = this.rateApiClient.getDataFromInternet();
        return this.cachedRates;
    }


}
