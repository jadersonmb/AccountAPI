package com.zuka.accountAPI.service;

import com.zuka.accountAPI.dto.AccountDTO;
import com.zuka.accountAPI.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

	AccountDTO save(AccountDTO accountDTO);
	Page<AccountDTO> listAll(Pageable pageable, AccountDTO filter);
	void delete(AccountDTO accountDTO);
	AccountDTO findById(Long id);
	void deleteList(List<Long> ids);
}
