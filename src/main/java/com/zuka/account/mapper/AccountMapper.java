package com.zuka.account.mapper;

import com.zuka.account.dto.AccountDTO;
import com.zuka.account.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO toAccountDTO(Account account);

    Account toAccount(AccountDTO accountDTO);
}
