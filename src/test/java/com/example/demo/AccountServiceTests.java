package com.example.demo;


import com.example.demo.feature.account.Account;
import com.example.demo.feature.account.AccountRepository;
import com.example.demo.feature.account.AccountService;

import com.example.demo.feature.account.BankAccountNotFoundException;
import com.example.demo.web.client.RatesRepository;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private RatesRepository ratesRepository;

    @InjectMocks
    private AccountService accountService;

    @Test(expected = BankAccountNotFoundException.class)
    public void accountNotFound(){
        final Account account1 = new Account();
        account1.setIban("RO46BTRLEURCRT0296582801");
        account1.setBalance(200.00);
        account1.setCurrency("USD");
        account1.setUpdateTime(LocalDateTime.parse("2019-12-03T10:15:30"));

        accountRepository.save(account1);

        Account searchedAccount = accountService.getEuroAccount("RO46BTRLEURCRT02965853450");
    }

    @Test
    public void accoundFoundAndRatesCached(){
        final Account account = new Account();
        account.setIban("RO46BTRLEURCRT02965853450");
        account.setBalance(10000.00);
        account.setCurrency("RON");
        account.setUpdateTime(LocalDateTime.parse("2016-07-03T10:15:30"));

        accountRepository.save(account);

        Account searchedAccount = accountService.getEuroAccount("RO46BTRLEURCRT02965853450");

        assertNotNull(ratesRepository.getCachedRates());
    }

    @Test
    public void accoundFoundAndRatesNotCached(){
        final Account account = new Account();
        account.setIban("RO46BTRLEURCRT02965853450");
        account.setBalance(10000.00);
        account.setCurrency("RON");
        account.setUpdateTime(LocalDateTime.parse("2016-07-03T10:15:30"));

        accountRepository.save(account);

        assertNull(ratesRepository.getCachedRates());

    }
}
