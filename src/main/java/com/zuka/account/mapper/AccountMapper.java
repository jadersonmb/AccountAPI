package com.zuka.account.mapper;

import com.zuka.accountAPI.dto.AccountDTO;
import com.zuka.accountAPI.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO toAccountDTO(Account account);

    Account toAccount(AccountDTO accountDTO);
}
