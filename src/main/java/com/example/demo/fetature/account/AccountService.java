package com.example.demo.fetature.account;

import com.example.demo.web.client.RatesDto;
import com.example.demo.web.client.RatesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final RatesRepository ratesRepository;


    public Account getAccount(String iban) throws BankAccountNotFoundException {
        return this.accountRepository.findById(iban)
                .orElseThrow(() -> new BankAccountNotFoundException(String.format("Account [%s] not found", iban)));
    }

    public Account getEuroAccount(String iban) throws BankAccountNotFoundException, RuntimeException {

        Account account = this.getAccount(iban);
        try {
            RatesDto rates = ratesRepository.getRates();
            double conversionRate = getConversionRate(rates, account.getCurrency());
            account.setBalance(account.getBalance() / conversionRate);
            account.setCurrency(rates.getBase());
        } catch (Exception e) {
            String errorMessage = "Error retrieving account information";
            log.error(errorMessage, e);
            throw new RuntimeException(errorMessage);
        }
        return account;
    }

    private double getConversionRate(RatesDto rates, String currency) {
        Double conversionRate = rates.getRates().get(currency);
        if (conversionRate > 0) {
            return conversionRate;
        } else {
            throw new RuntimeException("Unknown rate");
        }
    }
}
