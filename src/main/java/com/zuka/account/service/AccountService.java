package com.zuka.account.service;

import com.zuka.account.dto.AccountDTO;
import com.zuka.account.exception.AccountException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

	AccountDTO save(AccountDTO accountDTO) throws AccountException;
	Page<AccountDTO> listAll(Pageable pageable, AccountDTO filter) throws AccountException;
	void delete(AccountDTO accountDTO) throws AccountException;
	AccountDTO findById(Long id) throws AccountException;
	void deleteList(List<Long> ids) throws AccountException;
}
