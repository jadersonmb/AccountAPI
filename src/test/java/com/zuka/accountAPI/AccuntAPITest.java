package com.zuka.accountAPI;

import com.zuka.account.mapper.AccountMapper;
import com.zuka.accountAPI.dto.AccountDTO;
import com.zuka.accountAPI.enums.Sex;
import com.zuka.accountAPI.model.Account;
import com.zuka.accountAPI.model.Adress;
import com.zuka.accountAPI.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccuntAPITest  extends AccountApiApplicationTests {

    @Autowired
    public AccountService accountService;
    @Autowired
    public AccountMapper mapper;

    @Test
    public void createNewClient() {
        accountService.save(createAccount());
    }

    private AccountDTO createAccount() {
        return AccountDTO.builder()
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
