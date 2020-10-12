package com.zuka.account;

import com.zuka.account.enums.Sex;
import com.zuka.account.mapper.AccountMapper;
import com.zuka.account.model.Account;
import com.zuka.account.model.Adress;
import com.zuka.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccuntAPITest  extends AccountApiApplicationTests {

    @Autowired
    public AccountService accountService;
    @Autowired
    public AccountMapper mapper;

    @Test
    public void createNewClient() {
        accountService.save(mapper.toAccountDTO(createAccount()));
    }

    private Account createAccount() {
        return Account.builder()
                .name("Zuka")
                .sex(Sex.MALE)
                .adress(createAdress())
                .cellPhone("911566336")
                .cpf("07802054486")
                .build();
    }

    private Adress createAdress() {
        return Adress.builder()
                .andress("Rua Luís Serrão Pimentel")
                .postalCode("2800-699")
                .city("Lisboa")
                .number(13)
                .state("Almada")
                .build();
    }
}
