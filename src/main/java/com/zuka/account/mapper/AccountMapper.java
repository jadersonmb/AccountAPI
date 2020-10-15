package com.zuka.account.mapper;

import org.mapstruct.Mapper;

import com.zuka.account.dto.AccountDTO;
import com.zuka.account.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	
	 AccountDTO toAccountDTO(Account account);
	 Account toAccount(AccountDTO accoutDTO);

}
